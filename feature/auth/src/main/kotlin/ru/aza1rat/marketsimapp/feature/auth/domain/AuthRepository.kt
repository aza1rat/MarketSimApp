package ru.aza1rat.marketsimapp.feature.auth.domain

import ru.aza1rat.marketsimapp.core.domain.Result
import ru.aza1rat.marketsimapp.feature.auth.data.dto.AuthRequestDto
import ru.aza1rat.marketsimapp.feature.auth.domain.model.AuthError
import ru.aza1rat.marketsimapp.feature.auth.domain.model.UserInfo

interface AuthRepository {
    suspend fun login(loginDto: AuthRequestDto.LoginRequestDto): Result<UserInfo, AuthError>
    suspend fun register(registerDto: AuthRequestDto.RegisterRequestDto): Result<UserInfo, AuthError>
}
