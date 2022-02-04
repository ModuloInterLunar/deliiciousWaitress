package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network

import com.iesperemaria.modulointerlunar.deliiciouswaitress.core.RetrofitHelper
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Employee
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ingredient
import com.orhanobut.logger.Logger

class DeliiService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getEmployees():List<Employee>{
        val response = retrofit.getAllEmployees()
        Logger.i(response.toString())
        return response.body() ?: emptyList()
    }

    suspend fun getIngredients():List<Ingredient>{
        val response = retrofit.getAllIngredients()
        Logger.i(response.toString())
        return response.body() ?: emptyList()
    }

    suspend fun getEmployeeFromToken(): Employee {
        val response = retrofit.getEmployeeFromToken()
        Logger.i(response.toString())
        return response.body()!!
    }
}