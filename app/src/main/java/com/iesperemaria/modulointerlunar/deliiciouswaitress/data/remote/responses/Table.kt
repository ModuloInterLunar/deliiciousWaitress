package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

data class Table(
    var id: String = "",
    var actualTicket: Ticket? = null,
    var height: Int = 30,
    var width: Int = 30,
    var posX: Double = 0.0,
    var posY: Double = 0.0
)