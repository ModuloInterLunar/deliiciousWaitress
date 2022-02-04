package com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.exception

class DeliiApiException(message: String) : Exception(message)

class WrongCredentialsException(message: String) : Exception(message)

class NotEnoughStockException(message: String) : Exception(message)

class AlreadyInUseException(message: String) : Exception(message)