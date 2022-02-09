package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.model.AuthModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.model.OrderModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.model.TableModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.*
import retrofit2.Response
import retrofit2.http.*


interface DeliiApiClient {
    @POST("/login")
    suspend fun login(@Body auth: AuthModel): Response<Token>

    @GET("api/employees")
    suspend fun getAllEmployees(): Response<List<Employee>>

    @GET("api/ingredients")
    suspend fun getAllIngredients(): Response<List<Ingredient>>

    @GET("api/tables")
    suspend fun getAllTables(): Response<List<Table>>

    @GET("api/orders/cookednotserved")
    suspend fun getAllOrdersCookedNotServed(): Response<List<Order>>

    @POST("api/tables")
    suspend fun createTable(@Body table: TableModel): Response<Table>

    @GET("api/tables/{id}")
    suspend fun getTable(
        @Path("id") id: String
    ): Response<Table>

    @GET("api/employees/-1")
    suspend fun getEmployeeFromToken(): Response<Employee>

    @PATCH("api/orders/{id}")
    suspend fun patchOrder(
        @Body orderModel: OrderModel,
        @Path("id") id: String
    ): Response<Order>
}