package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.model.EmployeeModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.model.IngredientModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.GetEmployeesUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.util.Resource
import kotlinx.coroutines.launch

class EmployeeViewModel : ViewModel(){
    private val isLoading = MutableLiveData(false)
    private val loadError = MutableLiveData("")

    fun isLoading() : LiveData<Boolean> = isLoading
    fun loadError() : LiveData<String> = loadError

    val employeeModel = MutableLiveData<EmployeeModel>()

    var getEmployeesUseCase = GetEmployeesUseCase()

    fun onCreate() {
        viewModelScope.launch{
            isLoading.postValue(true)
            val result = getEmployeesUseCase()
            when(result) {
                is Resource.Success -> {
                    if (!result.data.isNullOrEmpty()){
                        val data = result.data
                        employeeModel.postValue(data[0])
                        isLoading.postValue(false)
                    }
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }
}