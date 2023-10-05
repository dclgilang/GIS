package id.co.dif.base_project.presentation.fragment

import android.content.Intent
import android.os.Bundle
import id.co.dif.base_project.R
import id.co.dif.base_project.base.BaseFragment
import id.co.dif.base_project.databinding.FragmentDashboardBinding
import id.co.dif.base_project.presentation.activity.SettingActivity
import id.co.dif.base_project.presentation.adapter.TitledViewPagerAdapter
import id.co.dif.base_project.viewmodel.MyDashboardViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DashboardFragment : BaseFragment<MyDashboardViewModel, FragmentDashboardBinding>(),
    KoinComponent {
    override val layoutResId = R.layout.fragment_dashboard
    private val fragmentMyDashboard: MyDashboardFragment by inject()
    private val fragmentTtDashboard: TTDashboardFragment by inject()

    override fun onViewBindingCreated(savedInstanceState: Bundle?) = with(binding) {
        val viewPagerAdapter = TitledViewPagerAdapter(childFragmentManager)
        val fragments =
            arrayListOf(fragmentMyDashboard, fragmentTtDashboard)
        val titles = arrayListOf(
            getString(R.string.my_dashboard), getString(R.string.tt_dashboard)
        )
        viewPagerAdapter.replaceAll(
            fragments, titles
        )
        pager.adapter = viewPagerAdapter
        pager.offscreenPageLimit = fragments.count()
        tabLayout.setupWithViewPager(pager)
        pager.isSaveEnabled = false

        binding.rootLayout.onActionButtonClicked = {
            startActivity(Intent(requireContext(), SettingActivity::class.java))
        }
    }
}