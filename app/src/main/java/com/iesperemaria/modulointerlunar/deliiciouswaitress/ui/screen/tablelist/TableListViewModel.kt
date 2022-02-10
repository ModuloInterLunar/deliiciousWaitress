package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.tablelist

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.tableusecase.CreateTableUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.tableusecase.GetTablesUseCase
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class TableListViewModel : ViewModel(){
    private val TAG = "TableListViewModel"
    private val isLoading = mutableStateOf(false)
    private val loadError = mutableStateOf("")

    fun isLoading(): MutableState<Boolean> = isLoading
    fun loadError() : MutableState<String> = loadError

    val timer = object : CountDownTimer(Long.MAX_VALUE, 1000) {
        override fun onTick(millisRemaning: Long) {
            loadTables()
            Log.i(TAG, "ticking")
        }

        override fun onFinish() {
        }
    }

    var tables by mutableStateOf(listOf<Table>())
    var getTablesUseCase = GetTablesUseCase()
    var createTableUseCase = CreateTableUseCase()

    fun loadTables(){
        viewModelScope.launch {
            isLoading.value = true
            try {
                val result = getTablesUseCase()
                if (!result.isNullOrEmpty()){
                    tables = result.toMutableList()
                    isLoading.value = false
                }
            } catch (e: Exception) {
                Logger.e(e.message ?: e.toString())
            }
        }
    }

    fun createTable(table: Table) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                createTableUseCase(table)
                tables = tables + table
                isLoading.value = false
            }catch (e: Exception){
                Logger.e(e.message ?: e.toString())
            }
        }
    }
}