package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.employeeusecase

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Employee

class LoginUseCase {
    private val api = DeliiService()

    suspend operator fun invoke(username: String, password: String): String = api.login(username, password)
}