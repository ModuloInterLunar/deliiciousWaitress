package com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.orderusecase

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.network.DeliiService
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Dish
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order

class GetDishesUseCase {
    private val api = DeliiService()
    suspend operator fun invoke(): List<Dish> = api.getDishes()
}