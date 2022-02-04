package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

data class Menu(
    val dishes: List<Dish>,
    val id: String,
    val price: Int
)