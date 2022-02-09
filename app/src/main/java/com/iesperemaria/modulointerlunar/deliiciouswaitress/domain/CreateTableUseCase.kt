package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table

class CreateTableUseCase {
    private val api = DeliiService()
    suspend operator fun invoke(table: Table): Table = api.createTable(table)
}