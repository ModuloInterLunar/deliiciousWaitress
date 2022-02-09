package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

data class Dish(
    var id: String = "",
    var description: String = "",
    var image: String = "",
    var ingredientQties: List<IngredientQty> = listOf(),
    var name: String = "",
    var price: Double = 0.0,
    var type: String = "Food"
)