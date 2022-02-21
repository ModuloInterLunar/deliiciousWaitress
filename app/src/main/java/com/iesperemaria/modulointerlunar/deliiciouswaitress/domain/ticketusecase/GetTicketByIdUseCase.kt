package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ticketusecase

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ticket

class GetTicketByIdUseCase {
    private val api = DeliiService()
    suspend operator fun invoke(id: String) : Ticket = api.getTicketById(id)
}