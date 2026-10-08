package ru.aza1rat.marketsimapp.feature.auth.impl.register.domain

import jakarta.inject.Inject
import ru.aza1rat.marketsimapp.feature.auth.impl.domain.AuthRepository

@Suppress("UnusedPrivateProperty")
class RegisterUserAuthUseCase @Inject constructor(private val repository: AuthRepository)
