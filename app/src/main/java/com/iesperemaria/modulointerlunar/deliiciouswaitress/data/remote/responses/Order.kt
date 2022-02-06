package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

data class Order(
    var id: String,
    var createdAt: String = "",
    var description: String = "",
    var dish: Dish,
    var employee: Employee,
    var hasBeenCoocked: Boolean,
    var hasBeenServed: Boolean,
    var isIncluded: Boolean,
    var table: String,
    var ticket: String,
    var updatedAt: String = ""
)