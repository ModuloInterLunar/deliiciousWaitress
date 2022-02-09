package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.dishselector

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Dish
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.GetTableByIdUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.orderusecase.GetDishesUseCase
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class DishSelectorViewModel : ViewModel() {
    private val isLoading = mutableStateOf(false)
    private val loadError = mutableStateOf("")

    fun isLoading() : MutableState<Boolean> = isLoading
    fun loadError() : MutableState<String> = loadError

    val dishes: MutableState<MutableList<Dish>> = mutableStateOf(mutableListOf())
    val getDishesUseCase = GetDishesUseCase()
    val table: MutableState<Table> = mutableStateOf(Table())
    val getTableByIdUseCase = GetTableByIdUseCase()
    val selectedDishes: MutableState<MutableList<Dish>> = mutableStateOf(mutableListOf())

    fun loadDishes(){
        viewModelScope.launch {
            isLoading.value = true
            try{
                val result = getDishesUseCase()
                if(!result.isNullOrEmpty()){
                    dishes.value = result.toMutableList()
                    isLoading.value = false
                }
            }catch (e: Exception) {
                Logger.e(e.message ?: e.toString())
            }
        }
    }

    fun loadTable(id: String){
        viewModelScope.launch {
            isLoading.value = true
            try{
                val result = getTableByIdUseCase(id)
                if(result != null){
                    table.value = result
                    isLoading.value = false
                }
            }catch (e: Exception) {
                Logger.e(e.message ?: e.toString())
            }
        }
    }
}