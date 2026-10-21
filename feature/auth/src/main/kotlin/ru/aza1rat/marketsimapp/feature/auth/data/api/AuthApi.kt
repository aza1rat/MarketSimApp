package ru.aza1rat.marketsimapp.feature.auth.data.api

import io.ktor.client.statement.HttpResponse
import ru.aza1rat.marketsimapp.feature.auth.data.dto.LoginRequestDto
import ru.aza1rat.marketsimapp.feature.auth.data.dto.MeRequestDto
import ru.aza1rat.marketsimapp.feature.auth.data.dto.RegisterRequestDto

interface AuthApi {
    suspend fun loginUser(loginRequestDto: LoginRequestDto): HttpResponse
    suspend fun registerUser(registerRequestDto: RegisterRequestDto): HttpResponse
    suspend fun getUserInfo(meRequestDto: MeRequestDto): HttpResponse
}
