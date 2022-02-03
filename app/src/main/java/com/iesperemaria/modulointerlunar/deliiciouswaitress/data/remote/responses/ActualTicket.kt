package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

data class ActualTicket(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val id: String,
    val isPaid: Boolean,
    val orders: List<Order>,
    val text: Any,
    val total: Int,
    val updatedAt: String
)