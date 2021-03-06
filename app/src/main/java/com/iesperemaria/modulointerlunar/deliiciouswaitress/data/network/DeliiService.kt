package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network

import android.util.Log
import com.google.gson.JsonObject
import com.iesperemaria.modulointerlunar.deliiciouswaitress.core.RetrofitHelper
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.exception.DeliiApiException
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.exception.ItemNotFoundException
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.exception.NotEnoughStockException
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.exception.WrongCredentialsException
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.model.*
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.*
import com.orhanobut.logger.Logger
import org.json.JSONObject

class DeliiService {
    private val TAG = "DeliiService"

    suspend fun login(username: String, password: String):String{
        val response = RetrofitHelper.getDeliiApiClient().login(AuthModel(username, password))
        Log.i(TAG, response.toString())
        val token = response.body()?.token
        if (response.code() == 200) {
            RetrofitHelper.setToken(token!!)
            return token
        } else {
            throw WrongCredentialsException(response.message())
        }
    }

    suspend fun getEmployees():List<Employee>{
        val response = RetrofitHelper.getDeliiApiClient().getAllEmployees()
        Log.i(TAG, response.toString())
        return response.body() ?: emptyList()
    }

    suspend fun getIngredients():List<Ingredient>{
        val response = RetrofitHelper.getDeliiApiClient().getAllIngredients()
        Log.i(TAG, response.toString())
        return response.body() ?: emptyList()
    }

    suspend fun getTables():List<Table>{
        val response = RetrofitHelper.getDeliiApiClient().getAllTables()
        Logger.i(response.toString())
        return response.body() ?: emptyList()
    }

    suspend fun createTable(table: Table):Table{
        val tableModel = TableModel(table)
        val response = RetrofitHelper.getDeliiApiClient().createTable(tableModel)
        Logger.i(response.toString())
        if(response.code() == 404)
            throw ItemNotFoundException(response.message())
        return response.body()!!
    }

    suspend fun getTableById(id: String) : Table {
        val response = RetrofitHelper.getDeliiApiClient().getTable(id)
        Logger.i(response.toString())
        if(response.code() == 404)
            throw ItemNotFoundException(response.message())
        return response.body()!!
    }

    suspend fun getEmployeeFromToken(): Employee {
        val response = RetrofitHelper.getDeliiApiClient().getEmployeeFromToken()
        Log.i(TAG, response.toString())
        if (response.code() != 200)
            throw DeliiApiException("authentication failed")
        return response.body()!!
    }

    suspend fun getOrdersCookedNotServed(): List<Order> {
        val response = RetrofitHelper.getDeliiApiClient().getAllOrdersCookedNotServed()
        Log.i(TAG, response.toString())
        return response.body() ?: emptyList()
    }

    suspend fun getDishes(): List<Dish> {
        val response = RetrofitHelper.getDeliiApiClient().getAllDishes()
        Log.i(TAG, response.toString())
        return response.body() ?: emptyList()
    }

    suspend fun updateOrder(order: Order): Order {
        val orderModel = OrderModel(order)
        val response = RetrofitHelper.getDeliiApiClient().patchOrder(orderModel, order.id)
        Log.i(TAG, response.toString())
        if (response.code() == 404)
            throw ItemNotFoundException((response.errorBody() as Message).message)
        return response.body()!!
    }

    suspend fun updateTicket(ticket: Ticket): Ticket {
        val ticketModel = TicketModel(ticket)
        val response = RetrofitHelper.getDeliiApiClient().patchTicket(ticketModel, ticket.id)
        Log.i(TAG, response.toString())
        if(response.code() == 404)
            throw ItemNotFoundException(response.message())
        return response.body()!!
    }

    suspend fun updateTable(table: Table): Table {
        val tableModel = TableModel(table)
        val response = RetrofitHelper.getDeliiApiClient().patchTable(tableModel, table.id)
        Log.i(TAG, response.toString())
        if(response.code() == 404)
            throw ItemNotFoundException(response.message())
        return response.body()!!
    }

    suspend fun updateIngredient(ingredient: Ingredient): Ingredient {
        val ingredientModel = IngredientModel(ingredient)
        val response = RetrofitHelper.getDeliiApiClient().patchIngredient(ingredientModel, ingredient.id)
        Log.i(TAG, response.toString())
        if(response.code() == 404)
            throw ItemNotFoundException(response.message())
        return response.body()!!
    }


    suspend fun deleteOrder(order: Order) {
        val response = RetrofitHelper.getDeliiApiClient().deleteOrder(order.id)
        Log.i(TAG, response.toString())
        if (response.code() == 404)
            throw ItemNotFoundException(response.message())
    }

    suspend fun createTicket(ticket: Ticket): Ticket {
        val ticketModel = TicketModel(ticket)
        val response = RetrofitHelper.getDeliiApiClient().createTicket(ticketModel)
        Log.i(TAG, response.toString())
        if (response.code() == 404)
            throw ItemNotFoundException(response.message())
        return response.body()!!
    }

    suspend fun reduceIngredientQuantity(dish: Dish) {
        dish.ingredientQties.map { ingredientQty ->
            val id = ingredientQty.ingredient.id
            val ingredientQtyModel = IngredientQtyModel(quantity = ingredientQty.quantity)
            val response = RetrofitHelper.getDeliiApiClient().reduceIngredientQuantity(ingredientQtyModel, id)
            if (response.code() == 400){
                val jObjError = JSONObject(response.errorBody()!!.string())
                throw NotEnoughStockException(jObjError.getString("message"))
            }
        }
    }

    suspend fun createOrder(order: Order): Order {
        val orderModel = OrderModel(order)
        val response = RetrofitHelper.getDeliiApiClient().createOrder(orderModel)
        Logger.i(response.toString())
        if(response.code() == 404)
            throw ItemNotFoundException(response.message())
        return response.body()!!
    }

    suspend fun getTicketPaid(): List<Ticket> {
        val response = RetrofitHelper.getDeliiApiClient().getAllTicketsPaid()
        Log.i(TAG, response.toString())
        return response.body() ?: emptyList()
    }

    suspend fun getTicketById(id: String): Ticket {
        val response = RetrofitHelper.getDeliiApiClient().getTicket(id)
        Logger.i(response.toString())
        if(response.code() == 404)
            throw ItemNotFoundException(response.message())
        return response.body()!!
    }

    suspend fun deleteTable(table: Table) {
        val response = RetrofitHelper.getDeliiApiClient().deleteTable(table.id)
        Log.i(TAG, response.toString())
        if (response.code() == 404)
            throw ItemNotFoundException(response.message())
    }

    suspend fun deleteTicket(ticket: Ticket) {
        val response = RetrofitHelper.getDeliiApiClient().deleteTicket(ticket.id)
        Log.i(TAG, response.toString())
        if (response.code() == 404)
            throw ItemNotFoundException(response.message())
    }

    suspend fun createIngredient(ingredient: Ingredient): Ingredient {
        val ingredientModel = IngredientModel(ingredient)
        val response = RetrofitHelper.getDeliiApiClient().createIngredient(ingredientModel)
        Logger.i(response.toString())
        if(response.code() == 404)
            throw ItemNotFoundException(response.message())
        if(response.code() != 201)
            throw DeliiApiException(response.message())
        return response.body()!!
    }
}