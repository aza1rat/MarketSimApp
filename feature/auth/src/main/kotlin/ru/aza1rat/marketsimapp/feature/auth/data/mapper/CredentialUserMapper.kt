package ru.aza1rat.marketsimapp.feature.auth.data.mapper

import ru.aza1rat.marketsimapp.feature.auth.domain.model.CredentialsUser
import ru.aza1rat.marketsimapp.feature.auth.login.data.dto.LoginRequestDto
import ru.aza1rat.marketsimapp.feature.auth.register.data.dto.RegisterRequestDto

fun CredentialsUser.toLoginRequest() = LoginRequestDto(
    email = this.email,
    password = this.password
)

fun CredentialsUser.toRegisterRequest() = RegisterRequestDto(
    firstName = this.email.substringBefore('@'),
    email = this.email,
    password = this.password,
)
