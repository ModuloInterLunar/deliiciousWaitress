package com.iesperemaria.modulointerlunar.deliiciouswaitress.data

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.model.EmployeeModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.model.IngredientModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.util.Resource

class DeliiRepository {

    private val api = DeliiService()

    suspend fun getAllEmployees(): Resource<List<EmployeeModel>> {
        val response = try {
            api.getEmployees()
        } catch (e: Exception) {
            return Resource.Error(e.message!!)
        }
        return Resource.Success(response)
    }

    suspend fun getAllIngredients(): Resource<List<IngredientModel>>{
        val response = try {
            api.getIngredients()
        } catch (e: Exception) {
            return Resource.Error(e.message!!)
        }
        return Resource.Success(response)
    }
}