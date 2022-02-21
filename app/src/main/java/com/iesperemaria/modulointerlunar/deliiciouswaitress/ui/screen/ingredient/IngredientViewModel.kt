package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.ingredient

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ingredient
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ingredientusecase.GetIngredientsUseCase
import kotlinx.coroutines.launch

class IngredientViewModel : ViewModel() {
    private val isLoading = mutableStateOf(false)
    private val loadError = mutableStateOf("")

    fun isLoading()  : MutableState<Boolean> = isLoading
    fun loadError() : MutableState<String> = loadError

    val ingredients: MutableState<MutableList<Ingredient>> = mutableStateOf(mutableListOf())

    fun loadIngredients(){
        viewModelScope.launch {
            val result = GetIngredientsUseCase()
            //ingredients.value = result.toMutableList()
        }
    }
}