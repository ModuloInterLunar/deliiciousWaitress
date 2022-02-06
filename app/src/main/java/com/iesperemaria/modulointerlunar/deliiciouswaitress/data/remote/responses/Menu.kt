package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

data class Menu(
    var dishes: List<Dish>,
    var id: String = "",
    var name: String,
    var price: Double,
    var image: String = ""
)