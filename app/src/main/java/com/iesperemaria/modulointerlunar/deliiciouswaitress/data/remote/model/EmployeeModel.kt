package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.model

import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Employee

data class EmployeeModel (
    var id: String = "",
    var username: String = "",
    var dni: String,
    var name: String,
    var surname: String,
    var password: String = "",
    var isAdmin: Boolean
) {
    constructor(employee: Employee) : this(
        id = employee.id,
        username = employee.username,
        dni = employee.dni,
        name = employee.name,
        surname = employee.surname,
        password = employee.password,
        isAdmin = employee.isAdmin
    )
}