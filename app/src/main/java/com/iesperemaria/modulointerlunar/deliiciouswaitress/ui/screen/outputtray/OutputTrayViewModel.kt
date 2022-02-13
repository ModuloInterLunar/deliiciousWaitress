package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.outputtray

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.orderusecase.GetOrdersCookedNotServedUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.orderusecase.UpdateOrderUseCase
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class OutputTrayViewModel : ViewModel() {
    private val TAG = "OutputTrayViewModel"
    private val isLoading = mutableStateOf(false)
    private val loadError = mutableStateOf("")

    fun isLoading(): MutableState<Boolean> = isLoading
    fun loadError() : MutableState<String> = loadError

    val timer = object : CountDownTimer(Long.MAX_VALUE, 1000) {
        override fun onTick(millisRemaning: Long) {
            loadOrders()
            Log.i(TAG,"ticking")
        }

        override fun onFinish() {
        }
    }

    var getOrdersCookedNotServedUseCase = GetOrdersCookedNotServedUseCase()

    var orders by mutableStateOf(listOf<Order>())
    val updatedOrder: MutableState<Order> = mutableStateOf(Order())
    var updateOrderUseCase = UpdateOrderUseCase()

    fun loadOrders(){
        viewModelScope.launch {
            isLoading.value = true
            try {
                val result = getOrdersCookedNotServedUseCase()
                orders = result
                isLoading.value = false
            } catch (e: Exception) {
                Logger.e(e.message ?: e.toString())
            }
        }
    }

    fun setServed(order: Order) {
        viewModelScope.launch {
            order.hasBeenServed = true
            isLoading.value = true
            try {
                val result = updateOrderUseCase(order)
                updatedOrder.value = result
                isLoading.value = false
            } catch (e: Exception) {
                Logger.e(e.message ?: e.toString())
            }
        }
    }

}