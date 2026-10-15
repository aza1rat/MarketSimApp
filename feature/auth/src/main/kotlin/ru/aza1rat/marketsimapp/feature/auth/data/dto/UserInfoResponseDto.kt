package ru.aza1rat.marketsimapp.feature.auth.data.dto

data class UserInfoResponseDto(
    val id: Int,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val role: String?
)
