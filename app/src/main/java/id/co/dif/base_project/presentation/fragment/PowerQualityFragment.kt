package id.co.dif.base_project.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.co.dif.base_project.R
import id.co.dif.base_project.base.BaseFragment
import id.co.dif.base_project.base.BaseViewModel
import id.co.dif.base_project.data.BaseData
import id.co.dif.base_project.databinding.FragmentPowerQualityBinding
import id.co.dif.base_project.presentation.adapter.DataTableAdapter

class PowerQualityFragment : BaseFragment<BaseViewModel, FragmentPowerQualityBinding>() {

    override val layoutResId = R.layout.fragment_power_quality
    lateinit var adapter: DataTableAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onViewBindingCreated(savedInstanceState: Bundle?) {

        adapter = DataTableAdapter()
        binding.rvDataTable.adapter = adapter

        binding.rvDataTable.layoutManager = LinearLayoutManager(requireContext())

        repeat(1){
            adapter.data.add(BaseData())
        }




    }

}