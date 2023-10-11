package id.co.dif.base_project.presentation.fragment

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.co.dif.base_project.R
import id.co.dif.base_project.base.BaseFragment
import id.co.dif.base_project.base.BaseViewModel
import id.co.dif.base_project.databinding.FragmentRealTimeReportBinding

class RealTimeReportFragment : BaseFragment<BaseViewModel, FragmentRealTimeReportBinding>() {
    override val layoutResId = R.layout.fragment_real_time_report

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {

        val videoURL = "https://media.lewatmana.com/cam/mirslipi/133/video-20000113-102049.384.mp4"

        // Convert the video URL to a URI
        val videoUri = Uri.parse(videoURL)
        binding.videoView.setVideoURI(videoUri)

//        val mediaController = MediaController(this)
//        binding.videoView.setMediaController(mediaController)
//
//        binding.videoView.setOnPreparedListener { mp ->
//            mp.isLooping = true
//        }
        binding.videoView.start()

        val range = com.ekn.gruzer.gaugelibrary.Range()
        range.color = Color.parseColor("#ce0000")
        range.from = 0.0
        range.to = 50.0

        val range2 = com.ekn.gruzer.gaugelibrary.Range()
        range2.color = Color.parseColor("#E3E500")
        range2.from = 50.0
        range2.to = 100.0

        val range3 = com.ekn.gruzer.gaugelibrary.Range()
        range3.color = Color.parseColor("#00b20b")
        range3.from = 100.0
        range3.to = 150.0

        //add color ranges to gauge
        binding.gauge.addRange(range)
        binding.gauge.addRange(range2)
        binding.gauge.addRange(range3)



        //set min max and current value
        binding.gauge.minValue = 0.0
        binding.gauge.maxValue = 150.0
        binding.gauge.value = 120.0



    }

}