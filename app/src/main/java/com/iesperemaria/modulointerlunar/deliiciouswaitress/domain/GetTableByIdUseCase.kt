package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table

class GetTableByIdUseCase {
    private val api = DeliiService()
    suspend operator fun invoke(id: String) : Table = api.getTableById(id)
}