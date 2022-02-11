package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.model

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ticket

data class TicketModel(
    var id: String,
    var isPaid: Boolean,
    var orders: List<String>,
    var text: String?,
    var total: Double,
)  {
    constructor(ticket: Ticket) : this(
        id = ticket.id,
        isPaid = ticket.isPaid,
        orders = ticket.orders?.map(Order::id) ?: emptyList(),
        text = ticket.text,
        total = ticket.total
    )
}
