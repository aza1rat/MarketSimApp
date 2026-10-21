package ru.aza1rat.marketsimapp.feature.auth.data.dto

import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class LoginResponseDto(
    val token: String?,
    val expiresAt: Instant,
    val user: UserInfoResponseDto
)
