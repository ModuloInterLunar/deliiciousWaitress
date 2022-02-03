package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.DeliiRepository
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.model.IngredientModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.util.Resource

class GetIngredientsUseCase {
    private val repository = DeliiRepository()

    suspend operator fun invoke(): Resource<List<IngredientModel>> = repository.getAllIngredients()
}