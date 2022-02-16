package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.orderusecase

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order

class CreateOrderUseCase {
    private val api = DeliiService()

    suspend operator fun invoke(order: Order): Order = api.createOrder(order)
}