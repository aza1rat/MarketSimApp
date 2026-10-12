package ru.aza1rat.marketsimapp.feature.auth.register.domain

import jakarta.inject.Inject
import ru.aza1rat.marketsimapp.feature.auth.domain.AuthRepository

@Suppress("UnusedPrivateProperty")
class RegisterUserAuthUseCase @Inject constructor(private val repository: AuthRepository)
