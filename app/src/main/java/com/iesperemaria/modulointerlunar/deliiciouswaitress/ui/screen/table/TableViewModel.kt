package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.table

import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.exception.ItemNotFoundException
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ticket
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.orderusecase.DeleteOrderUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.tableusecase.DeleteTableUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.tableusecase.GetTableByIdUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.tableusecase.UpdateTableUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ticketusecase.CreateTicketUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ticketusecase.DeleteTicketUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ticketusecase.UpdateTicketUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.AppScreens
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class TableViewModel : ViewModel() {
    private val TAG = "TableViewModel"
    lateinit var tableId: String
    private val isLoading = mutableStateOf(false)
    private val loadError = mutableStateOf("")

    fun isLoading(): MutableState<Boolean> = isLoading
    fun loadError(): MutableState<String> = loadError

    val timer = object : CountDownTimer(Long.MAX_VALUE, 1000) {
        override fun onTick(millisRemaning: Long) {
            loadTable(tableId)
            Log.i(TAG, "ticking")
        }

        override fun onFinish() {
        }
    }

    val table: MutableState<Table> = mutableStateOf(Table())
    val getTableByIdUseCase = GetTableByIdUseCase()
    val deleteOrderUseCase = DeleteOrderUseCase()
    val updateTicketUseCase = UpdateTicketUseCase()
    val updateTableUseCase = UpdateTableUseCase()
    val createTicketUseCase = CreateTicketUseCase()
    val deleteTableUseCase = DeleteTableUseCase()
    val deleteTicketUseCase = DeleteTicketUseCase()

    fun loadTable(id: String) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val result = getTableByIdUseCase(id)
                table.value = result
                isLoading.value = false
            } catch (e: Exception) {
                Logger.e(e.message ?: e.toString())
            }
        }
    }

    fun deleteOrder(order: Order) {
        viewModelScope.launch {
            try {
                val ticket = table.value.actualTicket!!
                deleteOrderUseCase(order)
                ticket.orders.remove(order)
                updateTicketUseCase(ticket)
            } catch (e: ItemNotFoundException) {
                //throw ItemNotFoundException("Error, ${e.message} not found.")
            }
        }
    }
    
    fun loadPaymentScreen(navController: NavController){
        if(table.value.actualTicket == null)
            return Toast.makeText(
                navController.context,
                "Error, la mesa ${table.value.id} no tiene ningún ticket.",
                Toast.LENGTH_SHORT
            ).show()

        navController.navigate( AppScreens.PaymentScreen.route + "/${table.value.actualTicket!!.id}")
    }

    fun loadDishSelectorScreen(navController: NavController){
        navController.navigate(
            AppScreens.DishSelectorScreen.route + "/${table.value.id}"
        )
    }

    fun createTicket(table: Table) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val ticket = createTicketUseCase(Ticket())
                table.actualTicket = ticket
                updateTableUseCase(table)
            }catch (e: Exception){
                Logger.e(e.message ?: e.toString())
            }
        }
    }

    fun deleteTable(onSuccessCallback: () -> Unit, onFailCallback: (e: Exception) -> Unit) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                val orders = table.value.actualTicket?.orders ?: emptyList()
                orders.forEach { order -> deleteOrder(order) }
                if (table.value.actualTicket != null)
                    deleteTicketUseCase(table.value.actualTicket!!)

                deleteTableUseCase(table.value)
                isLoading.value = false
                onSuccessCallback()
            } catch (e: Exception) {
                onFailCallback(e)
            }

        }
    }
}