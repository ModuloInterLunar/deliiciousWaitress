package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ingredient
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ingredientusecase.CreateIngredientUseCase
import kotlinx.coroutines.launch

class IngredientViewModel : ViewModel() {

    private val createIngredientUseCase = CreateIngredientUseCase()

    fun createIngredient(name: String, quantity: Double, measure: String, onSuccessCallback: () -> Unit, onErrorCallback: (message: String) -> Unit) {
        viewModelScope.launch {
            try {
                val ingredient = Ingredient(name = name, quantity = quantity, measure =  measure)
                createIngredientUseCase(ingredient)
                onSuccessCallback()
            } catch (e: Exception) {
                onErrorCallback(if (e.message == "Bad Request") "El ingrediente ya existe" else e.message ?: "Unknow exception")
            }
        }
    }
}