package ru.aza1rat.marketsimapp.feature.auth.data.mapper

import ru.aza1rat.marketsimapp.feature.auth.data.dto.LoginRequestDto
import ru.aza1rat.marketsimapp.feature.auth.data.dto.RegisterRequestDto
import ru.aza1rat.marketsimapp.feature.auth.domain.model.CredentialsUser

fun CredentialsUser.toLoginRequest() = LoginRequestDto(
    email = this.email,
    password = this.password
)

fun CredentialsUser.toRegisterRequest() = RegisterRequestDto(
    firstName = this.email.substringBefore('@'),
    email = this.email,
    password = this.password,
)
