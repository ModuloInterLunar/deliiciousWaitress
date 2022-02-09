package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ticketusecase

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ticket

class UpdateTicketUseCase {
    private val api = DeliiService()

    suspend operator fun invoke(ticket: Ticket): Ticket = api.updateTicket(ticket)
}