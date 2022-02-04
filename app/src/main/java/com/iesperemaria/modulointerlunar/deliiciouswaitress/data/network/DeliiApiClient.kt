package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.model.Auth
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Employee
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ingredient
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Token
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST




interface DeliiApiClient {
    @POST("/login")
    suspend fun login(@Body auth: Auth): Response<Token>

    @GET("api/employees")
    suspend fun getAllEmployees(): Response<List<Employee>>

    @GET("api/ingredients")
    suspend fun getAllIngredients(): Response<List<Ingredient>>

    @GET("api/employees/-1")
    suspend fun getEmployeeFromToken(): Response<Employee>
}