package ru.aza1rat.marketsimapp.core.session.dto

import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class Session(
    val token: String,
    val expiresAt: Instant,
    val userId: Int
)
