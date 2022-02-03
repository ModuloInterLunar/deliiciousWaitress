package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.model

data class EmployeeModel (
    var id: String,
    var username: String,
    var dni: String,
    var name: String,
    var surname: String,
    var password: String,
    var createdAt: String,
    var updatedAt: String,
    var isAdmin: Boolean
)