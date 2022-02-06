package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

data class Ingredient(
    var id: String = "",
    var measure: String,
    var name: String,
    var price: Int,
    var quantity: Double
)