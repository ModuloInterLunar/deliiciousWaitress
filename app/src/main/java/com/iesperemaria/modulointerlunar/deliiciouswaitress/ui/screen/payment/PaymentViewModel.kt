package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.payment

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.exception.ItemNotFoundException
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.tableusecase.GetTableByIdUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.tableusecase.UpdateTableUseCase
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class PaymentViewModel : ViewModel() {
    private val TAG = "PaymentViewModel"
    private val isLoading = mutableStateOf(false)
    private val loadError = mutableStateOf("")

    fun isLoading(): MutableState<Boolean> = isLoading
    fun loadError(): MutableState<String> = loadError

    val table: MutableState<Table> = mutableStateOf(Table())
    val getTableByIdUseCase = GetTableByIdUseCase()
    val updateTableUseCase = UpdateTableUseCase()

    fun loadTable(id: String) {
        viewModelScope.launch {
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
    }

    fun removeTicketFromTable(){
        viewModelScope.launch {
            try {
                table.value.actualTicket?.isPaid = true
                table.value.actualTicket = null
                table.value = updateTableUseCase(table.value)
            }
            catch (e: Exception) {
                Logger.e(e.message ?: e.toString())
            }
        }
    }
}