package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.tableusecase

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ingredient
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table

class GetTablesUseCase {
    private val api = DeliiService()
    suspend operator fun invoke(): List<Table> = api.getTables()
}