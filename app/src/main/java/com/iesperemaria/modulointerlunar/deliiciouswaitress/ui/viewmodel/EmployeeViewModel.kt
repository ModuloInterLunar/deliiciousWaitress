package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Employee
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.employeeusecase.GetEmployeesUseCase
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class EmployeeViewModel : ViewModel(){
    private val isLoading = MutableLiveData(false)
    private val loadError = MutableLiveData("")

    fun isLoading() : LiveData<Boolean> = isLoading
    fun loadError() : LiveData<String> = loadError

    val employeeModel = MutableLiveData<List<Employee>>()

    var getEmployeesUseCase = GetEmployeesUseCase()

    fun onCreate() {
        viewModelScope.launch{
            isLoading.value = true
            try {
                val result = getEmployeesUseCase()
                if (!result.isNullOrEmpty()){
                    employeeModel.value = result
                    isLoading.value = false
                }
            } catch (e: Exception) {
                Logger.e(e.message ?: e.toString())
            }
        }
    }
}