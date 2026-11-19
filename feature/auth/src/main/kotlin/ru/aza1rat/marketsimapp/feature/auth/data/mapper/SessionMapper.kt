package ru.aza1rat.marketsimapp.feature.auth.data.mapper

import ru.aza1rat.marketsimapp.core.session.dto.Session
import ru.aza1rat.marketsimapp.feature.auth.data.dto.LoginResponseDto

fun LoginResponseDto.toSession(): Session {
    val token = checkNotNull(token) {
        "Токен равен null"
    }

    return Session(
        token = token,
        expiresAt = expiresAt,
        userId = user.id
    )
}
