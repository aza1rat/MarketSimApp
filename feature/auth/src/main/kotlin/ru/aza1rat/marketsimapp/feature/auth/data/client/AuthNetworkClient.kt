package ru.aza1rat.marketsimapp.feature.auth.data.client

import ru.aza1rat.marketsimapp.core.network.client.NetworkClient
import ru.aza1rat.marketsimapp.core.network.client.NetworkResult
import ru.aza1rat.marketsimapp.core.network.client.safeApiCall
import ru.aza1rat.marketsimapp.core.network.dto.Response
import ru.aza1rat.marketsimapp.core.network.dto.toResponse
import ru.aza1rat.marketsimapp.feature.auth.data.api.AuthApi
import ru.aza1rat.marketsimapp.feature.auth.data.dto.LoginRequestDto
import ru.aza1rat.marketsimapp.feature.auth.data.dto.LoginResponseDto
import ru.aza1rat.marketsimapp.feature.auth.data.dto.MeRequestDto
import ru.aza1rat.marketsimapp.feature.auth.data.dto.RegisterRequestDto
import ru.aza1rat.marketsimapp.feature.auth.data.dto.UserInfoResponseDto
import javax.inject.Inject

class AuthNetworkClient @Inject constructor(
    private val authApi: AuthApi,
) : NetworkClient {
    override suspend fun <T> doRequest(dto: Any): NetworkResult<Response> {
        return when (dto) {
            is LoginRequestDto -> {
                safeApiCall(
                    request = { authApi.loginUser(dto) },
                    parse = { it.toResponse<LoginResponseDto>() }
                )
            }

            is RegisterRequestDto -> safeApiCall(
                request = { authApi.registerUser(dto) },
                parse = { it.toResponse<LoginResponseDto>() }
            )

            is MeRequestDto -> {
                safeApiCall(
                    request = { authApi.getUserInfo(dto) },
                    parse = { it.toResponse<UserInfoResponseDto>() }
                )
            }

            else -> throw IllegalArgumentException(UNKNOWN_DTO_TYPE_MESSAGE)
        }
    }

    private companion object {
        const val UNKNOWN_DTO_TYPE_MESSAGE = "Unknown DTO type"
    }
}
