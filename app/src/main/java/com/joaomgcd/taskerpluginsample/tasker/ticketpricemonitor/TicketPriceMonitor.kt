package com.joaomgcd.taskerpluginsample.tasker.ticketpricemonitor

import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.joaomgcd.taskerpluginlibrary.config.TaskerPluginConfig
import com.joaomgcd.taskerpluginlibrary.config.TaskerPluginConfigHelper
import com.joaomgcd.taskerpluginlibrary.input.TaskerInput
import com.joaomgcd.taskerpluginlibrary.output.TaskerOutputForConfig
import com.joaomgcd.taskerpluginlibrary.output.TaskerOutputsForConfig
import com.joaomgcd.taskerpluginsample.R
import com.joaomgcd.taskerpluginsample.databinding.ActivityConfigTicketPriceMonitorBinding
import com.joaomgcd.taskerpluginsample.tasker.ActivityConfigTasker
import com.joaomgcd.taskerpluginsample.tasker.getip.GetIPOutput
import java.util.Date

class TicketPriceMonitorHelper(config: TaskerPluginConfig<TicketPriceMonitorInput>) :
    TaskerPluginConfigHelper<TicketPriceMonitorInput, TicketPriceMonitorOutput, TicketPriceMonitorRunner>(
        config
    ) {
    override val runnerClass = TicketPriceMonitorRunner::class.java
    override val inputClass = TicketPriceMonitorInput::class.java
    override val outputClass = TicketPriceMonitorOutput::class.java

    //splitip output info is added dynamically depending on the split option in the input. Check the GetIpRunner to check how this is added as the output data
    override fun addOutputs(
        input: TaskerInput<TicketPriceMonitorInput>,
        output: TaskerOutputsForConfig
    ) {
        super.addOutputs(input, output)
        if (input.regular.departure != null && input.regular.departure != "") {
            output.add(
                TaskerOutputForConfig(
                    GetIPOutput.VAR_SPLIT_IP,
                    config.context.getString(R.string.splitip),
                    config.context.getString(
                        R.string.splitip_description
                    ),
                    true
                )
            )
        }
    }
}

class TicketPriceMonitorActivity :
    ActivityConfigTasker<TicketPriceMonitorInput, TicketPriceMonitorOutput, TicketPriceMonitorRunner, TicketPriceMonitorHelper, ActivityConfigTicketPriceMonitorBinding>() {
    //Overrides
    override fun getNewHelper(config: TaskerPluginConfig<TicketPriceMonitorInput>) =
        TicketPriceMonitorHelper(config)

    override fun assignFromInput(input: TaskerInput<TicketPriceMonitorInput>): Unit {
        var context = super.context
        input.regular.run {
//        binding?.?.setText(separator)
//        binding?.checkboxSplit?.isChecked = options.split

            val data = listOf("北京:BJS", "贵阳:KWE", "铜仁:TEN", "张家界:DYG", "湘西:DXJ")
            var adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, data)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            var spinner: Spinner = findViewById(R.id.departure_spinner)
            spinner.adapter = adapter

            var spinner1: Spinner = findViewById(R.id.destination_spinner)
            spinner1.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    // 处理选择的项
//                    println()
                    Toast.makeText(context, "选择了: $selectedItem", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // 处理没有选择的情况
                }
            }
        }
    }

    override val inputForTasker
        get() = TaskerInput(
            TicketPriceMonitorInput(
                binding?.departureSpinner?.getSelectedItem().toString(),
                binding?.destinationSpinner?.getSelectedItem().toString(),
                Date(),
                binding?.expectedScope?.getText().toString().toInt(),
                binding?.expectedPrice?.getText().toString().toInt(),
            )
        )
    override fun inflateBinding(layoutInflater: LayoutInflater) =
        ActivityConfigTicketPriceMonitorBinding.inflate(layoutInflater)

}
