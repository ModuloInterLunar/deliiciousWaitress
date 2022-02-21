package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.model.*
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

    @GET("api/dishes")
    suspend fun getAllDishes(): Response<List<Dish>>

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

    @DELETE("api/orders/{id}")
    suspend fun deleteOrder(
        @Path("id") id: String
    ): Response<Message>

    @PATCH("api/tickets/{id}")
    suspend fun patchTicket(
        @Body ticketModel: TicketModel,
        @Path("id") id: String
    ): Response<Ticket>

    @PATCH("api/ingredients/reduce/{id}")
    suspend fun reduceIngredientQuantity(
        @Body ingredientQtyModel: IngredientQtyModel,
        @Path("id") id: String
    ): Response<Message>

    @POST("api/orders")
    suspend fun createOrder(
        @Body orderModel: OrderModel
    ): Response<Order>

    @POST("api/tickets")
    suspend fun createTicket(
        @Body ticketModel: TicketModel
    ): Response<Ticket>

    @PATCH("api/tables/{id}")
    suspend fun patchTable(
        @Body tableModel: TableModel,
        @Path("id") id: String
    ): Response<Table>

    @GET("api/tickets/paid")
    suspend fun getAllTicketsPaid(): Response<List<Ticket>>

    @GET("api/tickets/{id}")
    suspend fun getTicket(
        @Path("id") id: String
    ): Response<Ticket>

    @DELETE("api/tables/{id}")
    suspend fun deleteTable(
        @Path("id") id: String
    ): Response<Message>

    @DELETE("api/tickets/{id}")
    suspend fun deleteTicket(
        @Path("id") id: String
    ): Response<Message>

}