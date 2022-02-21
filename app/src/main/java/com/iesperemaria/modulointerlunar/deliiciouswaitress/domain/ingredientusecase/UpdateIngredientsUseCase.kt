package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ingredientusecase

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ingredient

class UpdateIngredientsUseCase {
    private val api = DeliiService()

    suspend operator fun invoke(ingredient: Ingredient): Ingredient = api.updateIngredient(ingredient)
}