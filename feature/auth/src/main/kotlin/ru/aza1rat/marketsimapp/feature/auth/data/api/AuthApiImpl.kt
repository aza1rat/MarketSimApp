package ru.aza1rat.marketsimapp.feature.auth.data.api

import ru.aza1rat.marketsimapp.core.network.client.HttpMethod
import ru.aza1rat.marketsimapp.core.network.client.NetworkRequest
import ru.aza1rat.marketsimapp.feature.auth.data.dto.AuthRequestDto
import ru.aza1rat.marketsimapp.feature.auth.data.dto.MeRequestDto
import javax.inject.Inject

class AuthApiImpl @Inject constructor() : AuthApi {

    override fun loginUser(dto: AuthRequestDto.LoginRequestDto): NetworkRequest =
        NetworkRequest(
            method = HttpMethod.POST,
            path = LOGIN_PATH,
            isAuthorized = false,
            body = dto
        )

    override fun registerUser(dto: AuthRequestDto.RegisterRequestDto) =
        NetworkRequest(
            method = HttpMethod.POST,
            path = REGISTER_PATH,
            isAuthorized = false,
            body = dto
        )

    override fun getUserInfo(dto: MeRequestDto) =
        NetworkRequest(
            method = HttpMethod.GET,
            path = ME_PATH,
            isAuthorized = true,
            body = dto
        )

    private companion object {
        const val LOGIN_PATH = "api/Auth/login"
        const val REGISTER_PATH = "api/Auth/register"
        const val ME_PATH = "api/Auth/me"
    }
}
