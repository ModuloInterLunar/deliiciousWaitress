package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

data class Order(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val description: String,
    val dish: Dish,
    val employee: Employee,
    val hasBeenCoocked: Boolean,
    val hasBeenServed: Boolean,
    val id: String,
    val isIncluded: Boolean,
    val table: String,
    val ticket: String,
    val updatedAt: String
)