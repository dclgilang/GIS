package id.co.dif.base_project.presentation.activity

import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.annotation.RequiresApi
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.MPPointF
import id.co.dif.base_project.R
import id.co.dif.base_project.base.BaseActivity
import id.co.dif.base_project.base.BaseViewModel
import id.co.dif.base_project.data.TabMenuItem
import id.co.dif.base_project.databinding.ActivityHomeDetailInfoBinding
import id.co.dif.base_project.presentation.adapter.TitledViewPagerAdapter
import id.co.dif.base_project.presentation.adapter.ViewPagerAdapter
import id.co.dif.base_project.presentation.fragment.HistoryFragment
import id.co.dif.base_project.presentation.fragment.MapsTicketFragment
import id.co.dif.base_project.presentation.fragment.RealTimeReportFragment
import id.co.dif.base_project.presentation.fragment.ReportingDashboardFragment
import id.co.dif.base_project.presentation.fragment.SiteInfoFragment
import id.co.dif.base_project.presentation.fragment.UnitLocationFragment
import id.co.dif.base_project.viewmodel.MapSiteViewModel
import org.koin.core.component.inject
import java.awt.font.NumericShaper.Range

class HomeDetailInfoActivity : BaseActivity<MapSiteViewModel, ActivityHomeDetailInfoBinding>() {
    override val layoutResId = R.layout.activity_home_detail_info

//    private val realTimeReportFragment: RealTimeReportFragment by inject()
//    private lateinit var viewPagerAdapter: TitledViewPagerAdapter
//    private val pageTitles = listOf(
//        R.string.real_time_report,
//    )
//    private val fragments =
//        arrayListOf(
//            realTimeReportFragment
//        )
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
//        val tabMenuItems = mutableListOf<TabMenuItem>()
//        tabMenuItems.add(TabMenuItem(getString(R.string.real_time_monitor), RealTimeReportFragment()))
//    tabMenuItems.add(TabMenuItem(getString(R.string.unit_location), UnitLocationFragment()))
//
//        binding.viewPager.adapter = ViewPagerAdapter(this, supportFragmentManager, tabMenuItems)
//        binding.tabLayout.setupWithViewPager(binding.viewPager)

    viewModel.responseaGetSiteByid.observe(lifecycleOwner){
        if(it.status == 200) {
            preferences.siteData.value= it.data
            val tabMenuItems = mutableListOf<TabMenuItem>()
            tabMenuItems.add(TabMenuItem(getString(R.string.real_time_monitor), RealTimeReportFragment()))
            tabMenuItems.add(TabMenuItem(getString(R.string.unit_location),UnitLocationFragment()))
            tabMenuItems.add(TabMenuItem(getString(R.string.dashboard),ReportingDashboardFragment()))

            binding.viewPager.adapter = ViewPagerAdapter(this, supportFragmentManager, tabMenuItems)
            binding.tabLayout.setupWithViewPager(binding.viewPager)
        }
    }

    viewModel.getSiteById(preferences.selectedSite.value?.site_id)


}
}