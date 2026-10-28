package ru.aza1rat.marketsimapp.feature.auth.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponseDto(
    val id: Int,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val role: String?
)
