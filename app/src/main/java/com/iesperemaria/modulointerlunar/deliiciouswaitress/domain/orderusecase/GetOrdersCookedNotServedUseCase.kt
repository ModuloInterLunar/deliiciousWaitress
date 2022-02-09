package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.orderusecase

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Employee
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order

class GetOrdersCookedNotServedUseCase {
    private val api = DeliiService()

    suspend operator fun invoke(): List<Order> = api.getOrdersCookedNotServed()
}