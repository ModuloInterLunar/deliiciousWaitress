package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network

import android.util.Log
import com.iesperemaria.modulointerlunar.deliiciouswaitress.core.RetrofitHelper
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.exception.ItemNotFoundException
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.exception.WrongCredentialsException
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.model.AuthModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.model.OrderModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Employee
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ingredient
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.orhanobut.logger.Logger

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
        return response.body()!!
    }

    suspend fun getOrdersCookedNotServed(): List<Order> {
        val response = RetrofitHelper.getDeliiApiClient().getAllOrdersCookedNotServed()
        Log.i(TAG, response.toString())
        return response.body() ?: emptyList()
    }

    suspend fun updateOrder(order: Order): Order {
        val orderModel = OrderModel(order)
        val response = RetrofitHelper.getDeliiApiClient().patchOrder(orderModel, order.id)
        Log.i(TAG, response.toString())
        if(response.code() == 404)
            throw ItemNotFoundException(response.message())
        return response.body()!!
    }
}