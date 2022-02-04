package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

data class Ticket(
    val id: String,
    val createdAt: String,
    val isPaid: Boolean,
    val orders: List<Order>,
    val text: Any,
    val total: Int,
    val updatedAt: String
)