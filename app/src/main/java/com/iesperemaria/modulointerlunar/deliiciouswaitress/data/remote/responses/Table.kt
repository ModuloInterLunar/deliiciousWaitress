package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

data class Table(
    val id: String,
    val actualTicket: Ticket? = null,
    val height: Int = 25,
    val width: Int = 25,
    val posX: Double = 0.0,
    val posY: Double = 0.0
)