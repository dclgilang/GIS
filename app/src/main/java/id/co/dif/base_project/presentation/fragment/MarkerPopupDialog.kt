package id.co.dif.base_project.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import id.co.dif.base_project.R
import id.co.dif.base_project.base.BaseBottomSheetDialog
import id.co.dif.base_project.base.BaseViewModel
import id.co.dif.base_project.data.SiteDetails
import id.co.dif.base_project.data.MarkerTripleE
import id.co.dif.base_project.databinding.DialogMarkerPopUpBinding
import id.co.dif.base_project.presentation.activity.HomeDetailInfoActivity
import id.co.dif.base_project.presentation.activity.MapSiteActivity
import id.co.dif.base_project.utils.loadImage
import id.co.dif.base_project.utils.orDefault
import id.co.dif.base_project.utils.shimmerDrawable

class MarkerPopupDialog(val marker: MarkerTripleE?) :
    BaseBottomSheetDialog<BaseViewModel, DialogMarkerPopUpBinding, SiteDetails>() {

    override val layoutResId = R.layout.dialog_marker_pop_up

    var onSiteIsSelected: (marker: MarkerTripleE) -> Unit = { _ -> }
    var isSiteSelectable = false

    companion object {
        fun newInstance(marker: MarkerTripleE?) = MarkerPopupDialog(marker)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSelectThisSite.setOnClickListener {
            marker?.let {
                onSiteIsSelected(marker)
                dismiss()
            }
        }

        Log.d("TAG", "onViewCreated: ${marker?.image}")
        binding.imgIcon.loadImage(marker?.image, shimmerDrawable(), R.drawable.icon_excavator, circleCrop = true)
        binding.btnSelectThisSite.isVisible = isSiteSelectable
        binding.titleSelectThisSite.isVisible = isSiteSelectable
        binding.textAddress.text = marker?.site_addre_street
        binding.textPic.text = marker?.site_end_customer
        binding.contactPhone.text = marker?.site_contact_phone
        when (marker?.type) {
            "site",
            "TT Site All" -> {
                binding.textName.text = marker?.site_name
                val randomStrings = arrayOf(
                    "Tharik",
                    "Gilang",
                    "Ikky",
                    "Mirza",
                    "Adi"
                )

                val random = java.util.Random()
                val randomIndex = random.nextInt(randomStrings.size) // Generates a random index within the array size
                val randomString = randomStrings[randomIndex]
                binding.textPic.text = randomString
//                binding.textPic.text = marker?.site_end_customer
                binding.textAddress.text = marker?.site_addre_street
                binding.contactPhone.text = marker?.site_contact_phone.orDefault()
                binding.tvAddress.text = marker.site_address_kelurahan
            }

            "TT Map All" -> {
                binding.textName.text = marker?.site_name.orDefault()
                binding.textAddress.text = marker?.pgroup_nscluster
                binding.contactPhone.text = marker?.site_contact_phone
                binding.tvAddress.text = marker.site_address_kelurahan
            }

            "technician" -> {
                binding.textName.text = marker?.name.orDefault()
                binding.imgIcon.setImageResource(R.drawable.img_profile_technition)
            }

        }
    }

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        binding.btnDetail.setOnClickListener {
            if (marker?.type == "TT Site All") {
                preferences.selectedSite.value=marker
                startActivity(Intent(requireContext(), HomeDetailInfoActivity::class.java))
//                TicketListPopupDialog.newInstance(marker).show(
//                    childFragmentManager,
//                    TicketListPopupDialog::class.java.name
                //               )
            } else {
                preferences.selectedSite.value=marker
                TicketListPopupDialog.newInstance(marker).show(
                    childFragmentManager,
                    TicketListPopupDialog::class.java.name
                )
//                context?.startActivity(Intent(context, DetilTicketActivity::class.java))
//                startActivity(Intent(requireContext(), MapSiteActivity::class.java))
            }
        }
        binding.btnDetail.requestFocus()
    }


}

