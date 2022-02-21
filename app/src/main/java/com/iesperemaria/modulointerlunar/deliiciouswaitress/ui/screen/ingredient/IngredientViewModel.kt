package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.ingredient

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ingredient
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ingredientusecase.GetIngredientsUseCase
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ingredientusecase.UpdateIngredientsUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.orderusecase.DeleteOrderUseCase

class IngredientViewModel : ViewModel() {
    private val isLoading = mutableStateOf(false)
    private val loadError = mutableStateOf("")

    fun isLoading()  : MutableState<Boolean> = isLoading
    fun loadError() : MutableState<String> = loadError

    val ingredients: MutableState<MutableList<Ingredient>> = mutableStateOf(mutableListOf())
    val getIngredientsUseCase = GetIngredientsUseCase()
    val updateIngredientUseCase = UpdateIngredientsUseCase()

    fun loadIngredients(){
        viewModelScope.launch {
            val result = getIngredientsUseCase()
            ingredients.value = result.toMutableList()
        }
    }

    fun updateIngredient(ingredient: Ingredient){
        viewModelScope.launch {
            try {
                updateIngredientUseCase(ingredient)
            }
            catch (e: Exception) {
                Logger.e(e.message ?: e.toString())
            }
        }
    }
}