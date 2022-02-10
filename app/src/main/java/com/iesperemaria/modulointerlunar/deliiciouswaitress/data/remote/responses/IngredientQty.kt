package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

fun Double.format(): String {
    return if (this.toLong().toDouble() == this) String.format("%d", this.toLong()) else String.format("%s", this)
}

data class IngredientQty(
    var _id: String,
    var ingredient: Ingredient,
    var quantity: Double
) {
    fun formattedQuantity(): String = "${quantity.format()} ${ingredient.measure}"
    override fun toString(): String = "${ingredient.name} ${formattedQuantity()}"
}