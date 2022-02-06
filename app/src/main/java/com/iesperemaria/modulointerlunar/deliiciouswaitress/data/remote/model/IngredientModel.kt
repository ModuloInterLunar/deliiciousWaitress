package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.model

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ingredient

class IngredientModel (
    var id: String = "",
    var name: String,
    var quantity: Double,
    var measure: String
) {
    constructor(ingredient: Ingredient) : this(
        id = ingredient.id,
        name = ingredient.name,
        quantity = ingredient.quantity,
        measure = ingredient.measure
    )
}