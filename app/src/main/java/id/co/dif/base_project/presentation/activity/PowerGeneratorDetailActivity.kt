package id.co.dif.base_project.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.co.dif.base_project.R
import id.co.dif.base_project.base.BaseActivity
import id.co.dif.base_project.base.BaseViewModel
import id.co.dif.base_project.data.TabMenuItem
import id.co.dif.base_project.databinding.ActivityPowerGeneratorDetailBinding
import id.co.dif.base_project.presentation.adapter.ViewPagerAdapter
import id.co.dif.base_project.presentation.fragment.CctvFragment
import id.co.dif.base_project.presentation.fragment.EnergyMonitorFragment
import id.co.dif.base_project.presentation.fragment.RealTimeReportFragment
import id.co.dif.base_project.presentation.fragment.ReportingDashboardFragment
import id.co.dif.base_project.viewmodel.HomeViewModel
import id.co.dif.base_project.viewmodel.MapSiteViewModel

class PowerGeneratorDetailActivity : BaseActivity<MapSiteViewModel, ActivityPowerGeneratorDetailBinding>() {
    override val layoutResId = R.layout.activity_power_generator_detail

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {

        viewModel.responseaGetSiteByid.observe(lifecycleOwner){
            if(it.status == 200) {
                preferences.siteData.value= it.data
                val tabMenuItems = mutableListOf<TabMenuItem>()
                tabMenuItems.add(TabMenuItem(getString(R.string.energy_monitor), EnergyMonitorFragment()))
                binding.viewPager.adapter = ViewPagerAdapter(this, supportFragmentManager, tabMenuItems)
                binding.tabLayout.setupWithViewPager(binding.viewPager)
            }
        }

        viewModel.getSiteById(preferences.selectedSite.value?.site_id)

    }

}