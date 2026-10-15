package ru.aza1rat.marketsimapp.feature.auth.register.data.dto

data class RegisterRequestDto(
    val firstName: String,
    val lastName: String = "_",
    val email: String,
    val password: String,
    val phoneNumber: String? = null,
    val address: String? = null
)
