package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

data class Employee(
    var id: String = "",
    var username: String = "",
    var dni: String = "",
    var name: String = "",
    var surname: String = "",
    var password: String = "",
    var isAdmin: Boolean = false,
    var createdAt: String = "",
    var updatedAt: String = ""
) {
    fun fullName(): String = "$name $surname"
}