package ru.aza1rat.marketsimapp.feature.auth.login.domain

import jakarta.inject.Inject
import ru.aza1rat.marketsimapp.feature.auth.domain.AuthRepository

@Suppress("UnusedPrivateProperty")
class LoginUserAuthUseCase @Inject constructor(private val repository: AuthRepository)
