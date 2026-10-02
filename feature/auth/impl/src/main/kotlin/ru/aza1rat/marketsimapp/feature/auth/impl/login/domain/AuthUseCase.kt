package ru.aza1rat.marketsimapp.feature.auth.impl.login.domain

import jakarta.inject.Inject
import ru.aza1rat.marketsimapp.feature.auth.impl.domain.AuthRepository

class LoginUserAuthUseCase @Inject constructor(private val repository: AuthRepository)
