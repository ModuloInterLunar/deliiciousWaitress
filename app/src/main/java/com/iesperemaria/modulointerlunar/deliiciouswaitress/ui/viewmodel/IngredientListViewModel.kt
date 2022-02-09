package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.exception.WrongCredentialsException
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ingredient
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ingredientusecase.GetIngredientsUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.employeeusecase.LoginUseCase
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch


class IngredientListViewModel(application: Application) : AndroidViewModel(
    application
) {
    private val isLoading = mutableStateOf(false)
    private val loadError = mutableStateOf("")

    fun isLoading() : MutableState<Boolean> = isLoading
    fun loadError() : MutableState<String> = loadError

    val ingredients: MutableState<List<Ingredient>> = mutableStateOf(listOf())
    var getIngredientsUseCase = GetIngredientsUseCase()
    var loginUseCase = LoginUseCase()

    fun loadIngredients() {
        viewModelScope.launch{
            isLoading.value = true
            try {
                val token = loginUseCase("alvaro", "12345")
                Logger.i(token)
            } catch (e: WrongCredentialsException) {
                Toast.makeText(getApplication(), "Combinación usuario/contraseña inválida", Toast.LENGTH_LONG).show()
            }
            try {
                val result = getIngredientsUseCase()
                if (!result.isNullOrEmpty()) {
                    ingredients.value = result
                    isLoading.value = false
                }
            } catch (e: Exception) {
                Logger.e(e.message ?: e.toString())
            }
        }
    }
}