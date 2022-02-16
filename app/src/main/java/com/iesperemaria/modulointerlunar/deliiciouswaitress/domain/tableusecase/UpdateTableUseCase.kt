package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.tableusecase

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table

class UpdateTableUseCase {
    private val api = DeliiService()
    suspend operator fun invoke(table: Table): Table = api.updateTable(table)
}