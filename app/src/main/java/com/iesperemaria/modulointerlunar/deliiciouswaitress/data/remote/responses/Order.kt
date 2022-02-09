package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

data class Order(
    var id: String = "",
    var createdAt: String = "",
    var description: String = "",
    var dish: Dish = Dish(),
    var employee: Employee = Employee(),
    var hasBeenCooked: Boolean = false,
    var hasBeenServed: Boolean = false,
    var isIncluded: Boolean = false,
    var table: String = "",
    var ticket: String = "",
    var updatedAt: String = ""
)