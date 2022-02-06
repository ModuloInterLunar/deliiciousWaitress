package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.model

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Dish

data class DishModel(
    var id: String = "",
    var name: String,
    var type: String,
    var ingredientQties: List<IngredientQtyModel>,
    var price: Double,
    var description: String = "",
    var image: String = ""
) {
    constructor (dish: Dish) : this(
        id = dish.id,
        name = dish.name,
        type = dish.type,
        ingredientQties = dish.ingredientQties.map {
            IngredientQtyModel(it)
        },
        price = dish.price,
        description = dish.description,
        image = dish.image
    )
}