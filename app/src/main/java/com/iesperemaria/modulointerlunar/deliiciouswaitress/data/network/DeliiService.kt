package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network

import android.util.Log
import com.iesperemaria.modulointerlunar.deliiciouswaitress.core.RetrofitHelper
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.model.EmployeeModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.model.IngredientModel
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeliiService {
    private val TAG = "DeliiService"
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getEmployees():List<EmployeeModel>{
        val response = retrofit.getAllEmployees()
        Logger.i(response.toString())
        return response.body() ?: emptyList()
    }

    suspend fun getIngredients():List<IngredientModel>{
        val response = retrofit.getAllIngredients()
        Logger.i(response.toString())
        return response.body() ?: emptyList()
    }
}