package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

fun Double.format(digits: Int) = "%.${digits}f".format(this)

data class Dish(
    var id: String = "",
    var description: String = "",
    var image: String = "",
    var ingredientQties: List<IngredientQty> = listOf(),
    var name: String = "",
    var price: Double = 0.0,
    var type: String = "Food"
) {
    override fun toString(): String = "$name, $price."
    fun formatedPrice(): String = "${price.format(2)} €"
    fun getIngredients(): String = ingredientQties.fold("") { acc, cur -> acc + "${cur}\n"}
    fun getFullDescription(): String = "$description\n${getIngredients()}"
}