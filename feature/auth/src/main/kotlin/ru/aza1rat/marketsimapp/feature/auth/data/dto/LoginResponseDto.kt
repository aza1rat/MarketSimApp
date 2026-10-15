package ru.aza1rat.marketsimapp.feature.auth.data.dto

import kotlin.time.Instant

data class LoginResponseDto(
    val token: String?,
    val expiresAt: Instant,
    val user: UserInfoResponseDto
)
