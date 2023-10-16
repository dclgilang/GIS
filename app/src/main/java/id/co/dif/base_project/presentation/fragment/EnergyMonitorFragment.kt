package id.co.dif.base_project.presentation.fragment

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import id.co.dif.base_project.R
import id.co.dif.base_project.base.BaseFragment
import id.co.dif.base_project.databinding.FragmentEnergyMonitorBinding
import java.util.Random

class EnergyMonitorFragment : BaseFragment<EnergyMonitorViewModel, FragmentEnergyMonitorBinding>() {
    override val layoutResId = R.layout.fragment_energy_monitor
    private val handler: Handler = Handler()


    override fun onViewBindingCreated(savedInstanceState: Bundle?) {

        setdataTotalEnergy()
        setdataApparentEnergy()
        setdataReactiveEnergy()

        binding.btnDaily.setOnClickListener { generateAndSetChartData("Daily") }
        binding.btnWeekly.setOnClickListener { generateAndSetChartData("Weekly") }
        binding.btnMonthly.setOnClickListener { generateAndSetChartData("Monthly") }

        generateAndSetChartData("Daily")

//        // Dummy data\
//        val dailyEntries = ArrayList<Entry>()
//        for (day in 1..30) { // Assuming 30 days in a month
//            val consumption = random.nextFloat() * 250 // Random consumption value
//            dailyEntries.add(Entry(day.toFloat(), consumption))
//        }
//        val dailyDataSet = LineDataSet(dailyEntries, "Total Active Energy(kWh")
//        dailyDataSet.color = Color.YELLOW
//        dailyDataSet.lineWidth = 2f
//        dailyDataSet.setDrawValues(true)
//        dailyDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
//        val totalEntries = ArrayList<Entry>()
//        for (month in 1..30) {
//            val consumption = random.nextFloat() * 1000
//            totalEntries.add(Entry(month.toFloat(), consumption))
//        }
//        val totalDataSet = LineDataSet(totalEntries, "Total Reactive Energy")
//        totalDataSet.color = Color.BLUE
//        totalDataSet.lineWidth = 2f
//        totalDataSet.setDrawValues(true)
//        totalDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
//        //
//        val totalVoltData = LineDataSet(totalEntries, "Total Apparent Energy")
//        totalVoltData.color = Color.RED
//        totalVoltData.lineWidth = 2f
//        totalVoltData.setDrawValues(true)
//        totalVoltData.mode = LineDataSet.Mode.CUBIC_BEZIER
//        val totalHerzData = LineDataSet(totalEntries, "Total Herz ")
//        totalHerzData.color = Color.GREEN
//        totalHerzData.lineWidth = 2f
//        totalHerzData.setDrawValues(true)
//        totalHerzData.mode = LineDataSet.Mode.CUBIC_BEZIER
//        val lineDataFuelConsumtion = LineData(dailyDataSet, totalDataSet, totalVoltData, totalHerzData)
//
//        binding.comparechart.data = lineDataFuelConsumtion
//        binding.comparechart.invalidate()

    }

    private fun generateAndSetChartData(selectedPeriod: String) {
        val dataSetList = ArrayList<ILineDataSet>()
        val entriesActiveEnergy = ArrayList<Entry>()
        val entriesApparentEnergy = ArrayList<Entry>()
        val entriesReactiveEnergy = ArrayList<Entry>()
        val entriesHzData = ArrayList<Entry>()

        for (i in 0 until 30) { // Generate data for 30 days
            val activeEnergy = kotlin.random.Random.nextDouble(100.0, 500.0).toFloat() // Adjust the range as needed
            val apparentEnergy =  kotlin.random.Random.nextDouble(200.0, 800.0).toFloat()
            val reactiveEnergy =  kotlin.random.Random.nextDouble(50.0, 250.0).toFloat()

            entriesActiveEnergy.add(Entry(i.toFloat(), activeEnergy))
            entriesApparentEnergy.add(Entry(i.toFloat(), apparentEnergy))
            entriesReactiveEnergy.add(Entry(i.toFloat(), reactiveEnergy))
            entriesHzData.add(Entry(i.toFloat(), 60.0f)) // Stuck at 60 Hz
        }

        val colorActiveEnergy = Color.GREEN
        val colorApparentEnergy = Color.RED
        val colorReactiveEnergy = Color.YELLOW
        val colorHzData = Color.BLUE

        val dataSetActiveEnergy = LineDataSet(entriesActiveEnergy, "Active (kWh)")
         dataSetActiveEnergy.color = colorActiveEnergy
        dataSetActiveEnergy.setCircleColor(colorActiveEnergy)
        val dataSetApparentEnergy = LineDataSet(entriesApparentEnergy, "Apparent (kVAh)")
        dataSetApparentEnergy.color = colorApparentEnergy
        dataSetApparentEnergy.setCircleColor(colorApparentEnergy)
        val dataSetReactiveEnergy = LineDataSet(entriesReactiveEnergy, "Reactive (kVArh)")
        dataSetReactiveEnergy.color = colorReactiveEnergy
        dataSetReactiveEnergy.setCircleColor(colorReactiveEnergy)
        val dataSetHzData = LineDataSet(entriesHzData, "Frequency (Hz)")
        dataSetHzData.color = colorHzData
        dataSetHzData.setCircleColor(colorHzData)

        dataSetList.add(dataSetActiveEnergy)
        dataSetList.add(dataSetApparentEnergy)
        dataSetList.add(dataSetReactiveEnergy)
        dataSetList.add(dataSetHzData)

        val lineData = LineData(dataSetList)
        binding.comparechart.data = lineData
        binding.comparechart.invalidate()
    }

    private fun setdataTotalEnergy(){
        val updateDataRunnable = object : Runnable {
            override fun run() {
                val randomProgress = Random().nextInt(101)
                binding.power.progress = randomProgress
                val randomValue = Random().nextInt(1000)
                binding.valueTotalEnergy.text = "$randomValue kWh"
                binding.power.bottomText = "$randomValue kWh"
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(updateDataRunnable)

    }

    private fun setdataApparentEnergy(){
        val updateDataRunnable = object : Runnable {
            override fun run() {
                val randomProgress = Random().nextInt(1000 * 4)
                binding.frequency.progress = randomProgress
                val randomValue = Random().nextInt(1000)
                binding.valueApparentEnergy.text = "$randomValue V"
                binding.frequency.bottomText = "$randomValue V"
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(updateDataRunnable)
    }

    private fun setdataReactiveEnergy(){
        val updateDataRunnable = object : Runnable {
            override fun run() {
                val randomProgress = Random().nextInt(101 * 2)
                binding.reactive.progress = randomProgress
                val randomValue = Random().nextInt(1000)
                binding.valueReactiveEnergy.text = "$randomValue kvarh"
                binding.reactive.bottomText = "$randomValue kvarh"
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(updateDataRunnable)
    }

    private fun setdataVabEnergy(){
        val updateDataRunnable = object : Runnable {
            override fun run() {
                val randomProgress = Random().nextInt(101 * 2)
                binding.vab.progress = randomProgress
                val randomValue = Random().nextInt(1000)
                binding.valueVabEnergy.text = "$randomValue Hz"
                binding.reactive.bottomText = "$randomValue Hz"
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(updateDataRunnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }


}