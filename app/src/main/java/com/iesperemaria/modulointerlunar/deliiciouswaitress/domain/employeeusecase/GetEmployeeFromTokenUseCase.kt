package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.employeeusecase

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Employee

class GetEmployeeFromTokenUseCase {
    private val api = DeliiService()
    suspend operator fun invoke(): Employee = api.getEmployeeFromToken()
}