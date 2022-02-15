package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ticketusecase

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ticket

class GetTicketsPaidUseCase {
    private val api = DeliiService()

    suspend operator fun invoke(): List<Ticket> = api.getTicketPaid()
}