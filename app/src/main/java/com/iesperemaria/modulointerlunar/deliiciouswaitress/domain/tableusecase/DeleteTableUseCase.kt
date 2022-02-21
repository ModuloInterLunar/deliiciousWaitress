package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.tableusecase

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table

class DeleteTableUseCase {
    private val api = DeliiService()
    suspend operator fun invoke(table: Table) = api.deleteTable(table)
}