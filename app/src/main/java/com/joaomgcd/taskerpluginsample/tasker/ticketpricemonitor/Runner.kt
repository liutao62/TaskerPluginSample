package com.joaomgcd.taskerpluginsample.tasker.ticketpricemonitor

import android.content.Context
import com.joaomgcd.taskerpluginlibrary.action.TaskerPluginRunnerAction
import com.joaomgcd.taskerpluginlibrary.input.TaskerInput
import com.joaomgcd.taskerpluginlibrary.runner.TaskerPluginResult
import com.joaomgcd.taskerpluginlibrary.runner.TaskerPluginResultSucess
import com.joaomgcd.taskerpluginsample.R


class TicketPriceMonitorRunner : TaskerPluginRunnerAction<TicketPriceMonitorInput, TicketPriceMonitorOutput>() {

    //A custom notification icon is set for the foreground notification the action will have if the app targets API 26 or above
    override val notificationProperties get() = NotificationProperties(iconResId = R.drawable.plugin)
    override fun run(
        context: Context,
        input: TaskerInput<TicketPriceMonitorInput>
    ): TaskerPluginResult<TicketPriceMonitorOutput> {
        println(context)
        println(input)
        return TaskerPluginResultSucess(TicketPriceMonitorOutput())
    }

}