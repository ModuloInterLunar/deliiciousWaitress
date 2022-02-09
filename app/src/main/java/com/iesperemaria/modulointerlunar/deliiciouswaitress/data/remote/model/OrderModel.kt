package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.model

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Dish
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Employee
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order

data class OrderModel(
    var id: String,
    var dish: String,
    var employee: String,
    var hasBeenCooked: Boolean,
    var hasBeenServed: Boolean,
    var isIncluded: Boolean,
    var table: String,
    var ticket: String,
    var description: String = ""
) {
    constructor(order: Order) : this(
        id = order.id,
        dish = order.dish.id,
        employee = order.employee.id,
        hasBeenCooked = order.hasBeenCooked,
        hasBeenServed = order.hasBeenServed,
        isIncluded = order.isIncluded,
        table = order.table,
        ticket = order.ticket,
        description = order.description
    )
}
