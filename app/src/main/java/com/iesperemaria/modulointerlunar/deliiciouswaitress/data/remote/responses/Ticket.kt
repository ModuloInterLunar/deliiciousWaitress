package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

import com.iesperemaria.modulointerlunar.deliiciouswaitress.util.format

data class Ticket(
    var id: String = "",
    var createdAt: String = "",
    var isPaid: Boolean = false,
    var orders: MutableList<Order> = mutableListOf(),
    var text: String = "",
    var total: Double = 0.0,
    var updatedAt: String = ""
) {
    fun setTotal() {
        total = orders.fold(0.0) { acc, cur -> acc + cur.dish.price }
    }

    fun createdAtFormatted(): String = "${date()} a las ${hour()}"

    fun totalFormatted(): String = "${total.format(2)} €"
    fun date(): String = if (updatedAt.isNotEmpty()) updatedAt.subSequence(0,10).toString() else ""
    fun hour(): String = if (updatedAt.isNotEmpty()) updatedAt.subSequence(11,16).toString() else ""
    fun totalNoIvaFormatted(): String = (total / 1.1).format(2) + " €"
    fun ivaFormatted(): String = (total - total / 1.1).format(2) + " €"
}