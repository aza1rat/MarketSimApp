package ru.aza1rat.marketsimapp.feature.auth.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestDto(
    val firstName: String,
    val lastName: String = "_",
    val email: String,
    val password: String,
    val phoneNumber: String? = null,
    val address: String? = null
)
