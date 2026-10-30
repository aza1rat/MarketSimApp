package ru.aza1rat.marketsimapp.feature.auth.data.mapper

import ru.aza1rat.marketsimapp.feature.auth.data.dto.AuthRequestDto
import ru.aza1rat.marketsimapp.feature.auth.domain.model.CredentialsUser

fun CredentialsUser.toLoginRequest() = AuthRequestDto.LoginRequestDto(
    email = this.email.normalizeLogin(),
    password = this.password
)

fun CredentialsUser.toRegisterRequest() = AuthRequestDto.RegisterRequestDto(
    firstName = this.email.substringBefore('@'),
    lastName = this.email.substringBefore('@'),
    email = this.email.normalizeLogin(),
    password = this.password,
)

fun String.normalizeLogin(): String {
    return if (this.contains('@')) {
        this
    } else {
        "$this@example.com"
    }
}
