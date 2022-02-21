package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.payment

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.exception.ItemNotFoundException
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ticket
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.tableusecase.GetTableByIdUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.tableusecase.UpdateTableUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ticketusecase.GetTicketByIdUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ticketusecase.UpdateTicketUseCase
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class PaymentViewModel : ViewModel() {
    private val TAG = "PaymentViewModel"
    private val isLoading = mutableStateOf(false)
    private val loadError = mutableStateOf("")

    fun isLoading(): MutableState<Boolean> = isLoading
    fun loadError(): MutableState<String> = loadError

    val table: MutableState<Table> = mutableStateOf(Table())
    val ticket: MutableState<Ticket> = mutableStateOf(Ticket())
    val getTableByIdUseCase = GetTableByIdUseCase()
    val getTicketByIdUseCase = GetTicketByIdUseCase()
    val updateTableUseCase = UpdateTableUseCase()
    val updateTicketUseCase = UpdateTicketUseCase()


    fun loadTicket(id: String) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val result = getTicketByIdUseCase(id)
                ticket.value = result
                isLoading.value = false
            } catch (e: ItemNotFoundException) {
                throw ItemNotFoundException("Error, ticket not found.")
            } catch (e: Exception) {
                Logger.e(e.message ?: e.toString())
            }
        }
    }

    private suspend fun loadTable(id: String) {
        isLoading.value = true
        try {
            val result = getTableByIdUseCase(id)
            table.value = result
            isLoading.value = false
        } catch (e: ItemNotFoundException) {
            throw ItemNotFoundException("Error, table not found.")
        } catch (e: Exception) {
            Logger.e(e.message ?: e.toString())
        }
    }

    fun removeTicketFromTable(){
        viewModelScope.launch {
            loadTable(ticket.value.orders[0].table)
            try {
                ticket.value.isPaid = true
                ticket.value = updateTicketUseCase(ticket.value)
                table.value.actualTicket = null
                table.value = updateTableUseCase(table.value)
            }
            catch (e: Exception) {
                Logger.e(e.message ?: e.toString())
            }
        }
    }
}