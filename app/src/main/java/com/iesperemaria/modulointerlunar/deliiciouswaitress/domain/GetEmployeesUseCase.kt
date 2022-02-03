package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.DeliiRepository
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.model.EmployeeModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.model.EmployeeProvider
import com.iesperemaria.modulointerlunar.deliiciouswaitress.util.Resource

class GetEmployeesUseCase {

    private val repository = DeliiRepository()

    suspend operator fun invoke(): Resource<List<EmployeeModel>> = repository.getAllEmployees()
}