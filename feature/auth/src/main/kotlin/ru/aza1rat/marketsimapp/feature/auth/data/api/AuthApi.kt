package ru.aza1rat.marketsimapp.feature.auth.data.api

import ru.aza1rat.marketsimapp.core.network.client.NetworkRequest
import ru.aza1rat.marketsimapp.feature.auth.data.dto.AuthRequestDto
import ru.aza1rat.marketsimapp.feature.auth.data.dto.MeRequestDto

interface AuthApi {
    fun loginUser(dto: AuthRequestDto.LoginRequestDto): NetworkRequest
    fun registerUser(dto: AuthRequestDto.RegisterRequestDto): NetworkRequest
    fun getUserInfo(dto: MeRequestDto): NetworkRequest
}
