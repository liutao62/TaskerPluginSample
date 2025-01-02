package com.joaomgcd.taskerpluginsample.tasker.ticketpricemonitor

import com.joaomgcd.taskerpluginlibrary.input.TaskerInputField
import com.joaomgcd.taskerpluginlibrary.input.TaskerInputRoot
import java.util.Date

@TaskerInputRoot
class TicketPriceMonitorInput @JvmOverloads constructor(
    @field:TaskerInputField("departure", labelResIdName = "departure") var departure: String? = null,
    @field:TaskerInputField("destination", labelResIdName = "destination") var destination: String? = null,
    @field:TaskerInputField("expectDate", labelResIdName = "expectDate") var expectDate: Date? = null,
    @field:TaskerInputField("expectScope", labelResIdName = "expectScope") var expectScope: Int? = null,
    @field:TaskerInputField("expectPrice", labelResIdName = "expectPrice") var expectPrice: Int? = null,
)
