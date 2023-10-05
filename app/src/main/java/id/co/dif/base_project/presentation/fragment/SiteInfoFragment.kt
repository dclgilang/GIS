package id.co.dif.base_project.presentation.fragment

import android.os.Bundle
import id.co.dif.base_project.R
import id.co.dif.base_project.base.BaseFragment
import id.co.dif.base_project.data.MarkerTripleE
import id.co.dif.base_project.databinding.FragmentSiteInfoBinding
import id.co.dif.base_project.utils.orDefault
import id.co.dif.base_project.viewmodel.SiteInfoViewModel

class SiteInfoFragment : BaseFragment<SiteInfoViewModel, FragmentSiteInfoBinding>() {
    override val layoutResId = R.layout.fragment_site_info
    private var selectedSite: MarkerTripleE? = null

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {

        selectedSite = preferences.selectedSite.value

        viewModel.responseSiteInfo.observe(lifecycleOwner){
            if(it.status == 200) {
                val infoSite = it.data.info_site
                binding.nama.setText(infoSite.siteName.orDefault())
                binding.street.setText(infoSite.siteAddressStreet.orDefault())
                binding.kelurahan.setText(infoSite.siteAddressKelurahan.orDefault())
                binding.contacDesaInfo.setText(infoSite.siteBuildingType)
                binding.contacPhoneInfo.setText(infoSite.siteContactPhone.orDefault())
                binding.contacEmailInfo.setText(infoSite.siteAddressKecamatan.orDefault())
                binding.siteid.setText(infoSite.siteIdCustomer.orDefault())
                binding.tvProvider.setText(infoSite.siteProvider.orDefault())
                binding.area.setText(infoSite.pgroupNsCluster.orDefault())
                binding.cluster.setText(infoSite.pgroupCluster.orDefault())
                binding.latitude.setText(infoSite.technologyLatitude.orDefault())
                binding.longtitude.setText(infoSite.technologyLongitude.orDefault())
                binding.system.setText(infoSite.siteId.toString().orDefault())

            }
        }
    }
    override fun onResume() {
        super.onResume()
        viewModel.getSiteById(selectedSite?.site_id)
    }

}