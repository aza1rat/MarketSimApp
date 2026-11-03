package ru.aza1rat.marketsimapp.feature.auth.data.dto

import kotlinx.serialization.Serializable

sealed interface AuthRequestDto {
    @Serializable
    data class LoginRequestDto(
        val email: String,
        val password: String
    ) : AuthRequestDto

    @Serializable
    data class RegisterRequestDto(
        val firstName: String,
        val lastName: String = "_",
        val email: String,
        val password: String,
        val phoneNumber: String? = null,
        val address: String? = null
    ) : AuthRequestDto
}
