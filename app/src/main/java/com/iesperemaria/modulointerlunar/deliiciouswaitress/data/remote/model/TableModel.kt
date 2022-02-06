package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.model

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ticket

data class TableModel(
    var id: String = "",
    var actualTicket: String = "",
    var height: Int = 25,
    var width: Int = 25,
    var posX: Double = 0.0,
    var posY: Double = 0.0
) {
    constructor(table: Table) : this(
        id = table.id,
        actualTicket = table.actualTicket?.id ?: "",
        height = table.height,
        width = table.width,
        posX = table.posX,
        posY = table.posY
    )
}