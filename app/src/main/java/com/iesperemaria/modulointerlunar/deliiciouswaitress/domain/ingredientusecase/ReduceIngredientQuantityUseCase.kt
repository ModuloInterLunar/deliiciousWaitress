package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ingredientusecase

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Dish
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ingredient

class ReduceIngredientQuantityUseCase {
    private val api = DeliiService()
    suspend operator fun invoke(dish: Dish) = api.reduceIngredientQuantity(dish)
}