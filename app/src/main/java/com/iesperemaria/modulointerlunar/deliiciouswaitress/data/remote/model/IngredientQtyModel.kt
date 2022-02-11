package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.model

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.IngredientQty

data class IngredientQtyModel(
    var ingredient: String = "",
    var quantity: Double
) {
    constructor(ingredientQty: IngredientQty) : this(
        ingredient = ingredientQty.ingredient.id,
        quantity = ingredientQty.quantity
    )
}
