package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.model.EmployeeModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.model.IngredientModel
import retrofit2.Response
import retrofit2.http.GET

interface DeliiApiClient {
    @GET("api/employees")
    suspend fun getAllEmployees(): Response<List<EmployeeModel>>

    @GET("api/ingredients")
    suspend fun getAllIngredients(): Response<List<IngredientModel>>
}