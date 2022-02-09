package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.table

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.exception.ItemNotFoundException
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.GetTableByIdUseCase
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class TableViewModel : ViewModel() {
    private val TAG = "TableViewModel"
    lateinit var tableId: String
    private val isLoading = mutableStateOf(false)
    private val loadError = mutableStateOf("")

    fun isLoading() : MutableState<Boolean> = isLoading
    fun loadError() : MutableState<String> = loadError

    val timer = object : CountDownTimer(Long.MAX_VALUE, 1000) {
        override fun onTick(millisRemaning: Long) {
            loadTable(tableId)
            Log.i(TAG,"ticking")
        }

        override fun onFinish() {
        }
    }

    val table: MutableState<Table> = mutableStateOf(Table())
    val getTableByIdUseCase = GetTableByIdUseCase()

    fun loadTable(id: String){
        viewModelScope.launch {
            isLoading.value = true
            try{
                val result = getTableByIdUseCase(id)
                if(result != null){
                    table.value = result
                    isLoading.value = false
                }
            } catch (e: ItemNotFoundException) {
                throw ItemNotFoundException("Error, table not found.")
            }
            catch (e: Exception) {
                Logger.e(e.message ?: e.toString())
            }
        }
    }

    fun createTicket(){ /*TODO*/ }
}