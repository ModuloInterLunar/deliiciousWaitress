package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.model.EmployeeModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.model.IngredientModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Employee
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ingredient
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import retrofit2.Response
import retrofit2.http.GET

interface DeliiApiClient {
    @GET("api/employees")
    suspend fun getAllEmployees(): Response<List<Employee>>

    @GET("api/ingredients")
    suspend fun getAllIngredients(): Response<List<Ingredient>>

    @GET("api/tables")
    suspend fun getAllTables(): Response<List<Table>>

    @GET("api/employees/-1")
    suspend fun getEmployeeFromToken(): Response<Employee>
}