package ru.aza1rat.marketsimapp.feature.auth.data.api

import ru.aza1rat.marketsimapp.core.network.client.NetworkRequest
import ru.aza1rat.marketsimapp.feature.auth.data.dto.LoginRequestDto
import ru.aza1rat.marketsimapp.feature.auth.data.dto.MeRequestDto
import ru.aza1rat.marketsimapp.feature.auth.data.dto.RegisterRequestDto

interface AuthApi {
    fun loginUser(dto: LoginRequestDto): NetworkRequest
    fun registerUser(dto: RegisterRequestDto): NetworkRequest
    fun getUserInfo(dto: MeRequestDto): NetworkRequest
}
