package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

data class Ticket(
    var id: String,
    var createdAt: String,
    var isPaid: Boolean,
    var orders: MutableList<Order>,
    var text: String?,
    var total: Double,
    var updatedAt: String
)