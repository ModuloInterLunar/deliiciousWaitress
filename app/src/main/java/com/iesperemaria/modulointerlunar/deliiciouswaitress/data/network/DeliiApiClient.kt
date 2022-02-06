package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.model.AuthModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Employee
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ingredient
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface DeliiApiClient {
    @POST("/login")
    suspend fun login(@Body auth: AuthModel): Response<Token>

    @GET("api/employees")
    suspend fun getAllEmployees(): Response<List<Employee>>

    @GET("api/ingredients")
    suspend fun getAllIngredients(): Response<List<Ingredient>>

    @GET("api/tables")
    suspend fun getAllTables(): Response<List<Table>>

    @GET("api/tables/{id}")
    suspend fun getTable(
        @Path("id") id: String
    ): Response<Table>

    @GET("api/employees/-1")
    suspend fun getEmployeeFromToken(): Response<Employee>
}