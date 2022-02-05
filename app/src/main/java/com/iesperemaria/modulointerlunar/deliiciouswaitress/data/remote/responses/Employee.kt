package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses

data class Employee(
    val id: String,
    val createdAt: String = "",
    val dni: String,
    val isAdmin: Boolean = false,
    val name: String,
    val surname: String,
    val updatedAt: String = ""
)