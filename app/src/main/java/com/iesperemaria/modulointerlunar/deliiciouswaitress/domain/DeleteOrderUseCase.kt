package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order

class DeleteOrderUseCase {
    private val api = DeliiService()

    suspend operator fun invoke(order: Order) = api.deleteOrder(order)
}