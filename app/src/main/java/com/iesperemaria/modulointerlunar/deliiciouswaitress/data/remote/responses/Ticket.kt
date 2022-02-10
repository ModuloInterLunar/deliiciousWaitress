package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

import com.iesperemaria.modulointerlunar.deliiciouswaitress.util.format

data class Ticket(
    var id: String,
    var createdAt: String,
    var isPaid: Boolean,
    var orders: MutableList<Order>,
    var text: String?,
    var total: Double,
    var updatedAt: String
) {
    fun setTotal() {
        total = orders.fold(0.0) { acc, cur -> acc + cur.dish.price }
    }

    fun totalFormatted(): String = "${total.format(2)} €"
}