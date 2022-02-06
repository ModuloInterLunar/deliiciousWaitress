package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.model

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Dish
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Menu

data class MenuModel(
    var id: String = "",
    var price: Double,
    var dishes: List<String>,
    var image: String = ""
) {
    constructor(menu: Menu) : this(
        id = menu.id,
        price = menu.price,
        dishes = menu.dishes.map(Dish::id),
        image = menu.image
    )
}
