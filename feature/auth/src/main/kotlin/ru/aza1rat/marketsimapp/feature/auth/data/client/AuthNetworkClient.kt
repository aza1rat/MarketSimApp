package ru.aza1rat.marketsimapp.feature.auth.data.client

import ru.aza1rat.marketsimapp.core.network.client.NetworkClient
import ru.aza1rat.marketsimapp.core.network.client.NetworkExecutor
import ru.aza1rat.marketsimapp.core.network.client.NetworkResult
import ru.aza1rat.marketsimapp.core.network.dto.Response
import ru.aza1rat.marketsimapp.feature.auth.data.api.AuthApi
import ru.aza1rat.marketsimapp.feature.auth.data.dto.LoginRequestDto
import ru.aza1rat.marketsimapp.feature.auth.data.dto.MeRequestDto
import ru.aza1rat.marketsimapp.feature.auth.data.dto.RegisterRequestDto
import javax.inject.Inject

class AuthNetworkClient @Inject constructor(
    private val authApi: AuthApi,
    private val networkExecutor: NetworkExecutor
) : NetworkClient {
    override suspend fun <T> doRequest(dto: Any): NetworkResult<Response> {
        return when (dto) {
            is LoginRequestDto -> {
                networkExecutor.execute(
                    request = authApi.loginUser(dto),
                    serializer = LoginRequestDto.serializer()
                )
            }

            is RegisterRequestDto -> {
                networkExecutor.execute(
                    request = authApi.registerUser(dto),
                    serializer = RegisterRequestDto.serializer()
                )
            }

            is MeRequestDto -> {
                networkExecutor.execute(
                    request = authApi.getUserInfo(dto),
                    serializer = MeRequestDto.serializer()
                )
            }

            else -> throw IllegalArgumentException(UNKNOWN_DTO_TYPE_MESSAGE)
        }
    }

    private companion object {
        const val UNKNOWN_DTO_TYPE_MESSAGE = "Unknown DTO type"
    }
}
