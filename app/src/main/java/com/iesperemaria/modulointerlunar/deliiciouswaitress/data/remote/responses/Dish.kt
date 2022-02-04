package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

data class Dish(
    val __v: Int,
    val _id: String,
    val description: String,
    val id: String,
    val image: String,
    val ingredientQties: List<IngredientQty>,
    val name: String,
    val price: Double,
    val type: String
)