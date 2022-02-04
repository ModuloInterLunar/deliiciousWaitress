package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.model.IngredientModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Employee

class GetEmployeeFromTokenUseCase {
    private val api = DeliiService()

    suspend operator fun invoke(): Employee = api.getEmployeeFromToken()
}