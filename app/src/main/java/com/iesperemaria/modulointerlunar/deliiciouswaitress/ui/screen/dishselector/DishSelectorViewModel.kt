package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.dishselector

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.exception.NotEnoughStockException
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Dish
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Employee
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.employeeusecase.GetEmployeeFromTokenUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ingredientusecase.ReduceIngredientQuantityUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.orderusecase.CreateOrderUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.tableusecase.GetTableByIdUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.dishusecase.GetDishesUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ticketusecase.UpdateTicketUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class DishSelectorViewModel : ViewModel() {
    private val isLoading = mutableStateOf(false)
    private val loadError = mutableStateOf("")

    fun isLoading() : MutableState<Boolean> = isLoading
    fun loadError() : MutableState<String> = loadError

    val getDishesUseCase = GetDishesUseCase()
    val getTableByIdUseCase = GetTableByIdUseCase()
    val getEmployeeFromTokenUseCase = GetEmployeeFromTokenUseCase()
    val reduceIngredientQuantityUseCase = ReduceIngredientQuantityUseCase()
    val createOrderUseCase = CreateOrderUseCase()
    val updateTicketUseCase = UpdateTicketUseCase()

    var dishes by mutableStateOf(listOf<Dish>())
    var shownDishes by mutableStateOf(listOf<Dish>())
    val table: MutableState<Table> = mutableStateOf(Table())
    val employee: MutableState<Employee> = mutableStateOf(Employee())
    var selectedOrders by mutableStateOf(listOf<Order>())

    fun loadDishes(){
        viewModelScope.launch {
            isLoading.value = true
            try{
                val result = getDishesUseCase()
                if(!result.isNullOrEmpty()){
                    dishes = result.toMutableList()
                    shownDishes = result.toMutableList()
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
        selectedOrders = selectedOrders + Order(dish = dish, table = table.value.id, _id = System.currentTimeMillis())
    }

    fun sendOrders(context: Context, callback: () -> Unit) {
        viewModelScope.launch {
            isLoading.value = true
            val ticket = table.value.actualTicket!!
            selectedOrders.forEach { order ->
                try {
                    reduceIngredientQuantityUseCase(order.dish)
                } catch (e: NotEnoughStockException) {
                    Toast.makeText(context, context.getString(R.string.not_enough_stock_exception_message, e.message!!.split("'")[1], order.dish.name), Toast.LENGTH_LONG).show()
                    return@forEach
                }
                order.ticket = ticket.id
                order.employee = employee.value
                try {
                    val updatedOrder = createOrderUseCase(order)
                    ticket.orders.add(updatedOrder)
                } catch (e: Exception){
                    Logger.e(e.message ?: e.toString())
                }

            }
            updateTicketUseCase(ticket)
            selectedOrders = listOf()
            callback()
        }
    }
}