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

    fun createdAtFormatted(): String = "${createdAt.subSequence(0,10)} a las ${createdAt.subSequence(11,16)}"

    fun totalFormatted(): String = "${total.format(2)} €"
}