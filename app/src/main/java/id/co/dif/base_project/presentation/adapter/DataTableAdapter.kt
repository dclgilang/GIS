package id.co.dif.base_project.presentation.adapter

import id.co.dif.base_project.R
import id.co.dif.base_project.base.BaseAdapter
import id.co.dif.base_project.base.BaseViewModel
import id.co.dif.base_project.data.BaseData
import id.co.dif.base_project.databinding.ItemDataInTableBinding

class DataTableAdapter(): BaseAdapter<BaseViewModel, ItemDataInTableBinding, BaseData>() {

    override val layoutResId = R.layout.item_data_in_table


    override fun getItemViewType(position: Int): Int = 0
    override fun getItemCount(): Int = data.size

    override fun onLoadItem(
        binding: ItemDataInTableBinding,
        data: MutableList<BaseData>,
        position: Int
    ) {
        binding.no.setText("1")
        binding.timestamp.setText("2022-02-23 07:50:18")
        binding.parameter.setText("Total Active Energy (kWh)")
        binding.value.setText("19,147,470,981")

    }
}