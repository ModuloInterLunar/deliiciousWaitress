package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.tablelist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.GetTablesUseCase
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class TableListViewModel : ViewModel(){
    private val isLoading = mutableStateOf(false)
    private val loadError = mutableStateOf("")

    fun isLoading(): MutableState<Boolean> = isLoading
    fun loadError() : MutableState<String> = loadError

    val tables: MutableState<List<Table>> = mutableStateOf(listOf())
    var getTablesUseCase = GetTablesUseCase()

    fun loadTables(){
        viewModelScope.launch {
            isLoading.value = true
            try {
                val result = getTablesUseCase()
                if (!result.isNullOrEmpty()){
                    tables.value = result
                    isLoading.value = false
                }
            } catch (e: Exception) {
                Logger.e(e.message ?: e.toString())
            }
        }
    }
}