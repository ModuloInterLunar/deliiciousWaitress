package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ticketusecase

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ticket

class DeleteTicketUseCase {
    private val api = DeliiService()
    suspend operator fun invoke(ticket: Ticket) = api.deleteTicket(ticket)
}