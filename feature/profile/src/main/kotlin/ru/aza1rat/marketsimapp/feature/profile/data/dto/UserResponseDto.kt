package ru.aza1rat.marketsimapp.feature.profile.data.dto

import kotlin.time.Instant

data class UserResponseDto(
    val id: Int,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val phoneNumber: String?,
    val address: String?,
    val roleName: String?,
    val createdAt: Instant
)
