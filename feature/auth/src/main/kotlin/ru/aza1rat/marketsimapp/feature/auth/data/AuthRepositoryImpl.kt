package ru.aza1rat.marketsimapp.feature.auth.data

import jakarta.inject.Inject
import ru.aza1rat.marketsimapp.core.domain.Result
import ru.aza1rat.marketsimapp.core.network.client.NetworkResult
import ru.aza1rat.marketsimapp.core.network.dto.Response
import ru.aza1rat.marketsimapp.core.network.dto.toErrorsString
import ru.aza1rat.marketsimapp.feature.auth.data.client.AuthNetworkClient
import ru.aza1rat.marketsimapp.feature.auth.data.dto.AuthRequestDto
import ru.aza1rat.marketsimapp.feature.auth.data.dto.LoginResponseDto
import ru.aza1rat.marketsimapp.feature.auth.data.mapper.toUserInfoDomain
import ru.aza1rat.marketsimapp.feature.auth.domain.AuthRepository
import ru.aza1rat.marketsimapp.feature.auth.domain.isLoginValid
import ru.aza1rat.marketsimapp.feature.auth.domain.isPasswordValid
import ru.aza1rat.marketsimapp.feature.auth.domain.model.AuthError
import ru.aza1rat.marketsimapp.feature.auth.domain.model.UserInfo

class AuthRepositoryImpl @Inject constructor(val client: AuthNetworkClient) : AuthRepository {
    override suspend fun login(loginDto: AuthRequestDto.LoginRequestDto): Result<UserInfo, AuthError> {
        validateCredentials(loginDto.email, loginDto.password)?.let {
            return it
        }

        return processResult(loginDto)
    }

    override suspend fun register(registerDto: AuthRequestDto.RegisterRequestDto): Result<UserInfo, AuthError> {
        validateCredentials(registerDto.email, registerDto.password)?.let {
            return it
        }

        return processResult(registerDto)
    }

    private fun validateCredentials(
        login: String,
        password: String
    ): Result.Error<UserInfo, AuthError>? {
        return when {
            !login.isLoginValid() -> Result.Error<UserInfo, AuthError>(AuthError.ValidationError.INVALID_LOGIN)
            !password.isPasswordValid() -> Result.Error<UserInfo, AuthError>(AuthError.ValidationError.INVALID_PASSWORD)
            else -> null
        }
    }

    private suspend fun processResult(dto: AuthRequestDto): Result<UserInfo, AuthError> {
        return when (val result = client.doRequest<LoginResponseDto>(dto)) {
            is NetworkResult.Success<*> -> {
                val resultResponseDto = result.data as? Response.ResultResponseDto<*>
                val resultDto = resultResponseDto?.data as? LoginResponseDto
                val user = resultDto?.toUserInfoDomain()
                if (user == null) {
                    val code = result.statusCode
                    val message = resultResponseDto?.message
                    Result.Error<UserInfo, AuthError>(code.toError(message))
                } else {
                    Result.Success<UserInfo, AuthError>(user)
                }
            }

            is NetworkResult.HttpError -> {
                val code = result.statusCode
                val error =
                    result.response.errors.orEmpty().firstOrNull()?.let { code.toError(it) }
                if (error != null) {
                    Result.Error<UserInfo, AuthError>(error)
                } else {
                    Result.Error<UserInfo, AuthError>(AuthError.Unknown(result.response.errors.toErrorsString()))
                }
            }

            NetworkResult.ConnectionProblems -> Result.Error<UserInfo, AuthError>(AuthError.NetworkError.CONNECTION)
            NetworkResult.SerializationError -> Result.Error<UserInfo, AuthError>(AuthError.SerializationError)
            NetworkResult.Timeout -> Result.Error<UserInfo, AuthError>(AuthError.NetworkError.REQUEST_TIME_OUT)
        }
    }
}
