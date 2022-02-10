package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.dishselector

import android.os.CountDownTimer
import androidx.compose.runtime.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Dish
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Employee
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.employeeusecase.GetEmployeeFromTokenUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.tableusecase.GetTableByIdUseCase
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
    val employee: MutableState<Employee> = mutableStateOf(Employee())
    val getEmployeeFromTokenUseCase = GetEmployeeFromTokenUseCase()
    var selectedOrders by mutableStateOf(listOf<Order>())

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

    fun loadEmployee(){
        isLoading.value = true
        viewModelScope.launch {
            try{
                val result = getEmployeeFromTokenUseCase()
                employee.value = result
                isLoading.value = false
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
                table.value = result
                isLoading.value = false
            }catch (e: Exception) {
                Logger.e(e.message ?: e.toString())
            }
        }
    }

    fun removeOrder(order: Order) {
        // we create a new list to trigger the state change
        selectedOrders = selectedOrders.toMutableList().also { it.remove(order) }
    }

    fun addOrder(dish: Dish) {
        // we create a new list to trigger the state change
        selectedOrders = selectedOrders + Order(dish = dish, table = table.value.id, _id = selectedOrders.size)
    }
}