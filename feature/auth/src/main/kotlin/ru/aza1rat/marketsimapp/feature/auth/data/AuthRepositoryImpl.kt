package ru.aza1rat.marketsimapp.feature.auth.data

import io.ktor.utils.io.CancellationException
import jakarta.inject.Inject
import ru.aza1rat.marketsimapp.core.domain.Result
import ru.aza1rat.marketsimapp.core.network.client.NetworkResult
import ru.aza1rat.marketsimapp.core.network.dto.Response
import ru.aza1rat.marketsimapp.core.network.dto.toErrorMessages
import ru.aza1rat.marketsimapp.core.network.dto.toErrorsString
import ru.aza1rat.marketsimapp.core.session.api.SessionManager
import ru.aza1rat.marketsimapp.feature.auth.data.client.AuthNetworkClient
import ru.aza1rat.marketsimapp.feature.auth.data.dto.AuthRequestDto
import ru.aza1rat.marketsimapp.feature.auth.data.dto.LoginResponseDto
import ru.aza1rat.marketsimapp.feature.auth.data.mapper.toError
import ru.aza1rat.marketsimapp.feature.auth.data.mapper.toSession
import ru.aza1rat.marketsimapp.feature.auth.data.mapper.toUserInfoDomain
import ru.aza1rat.marketsimapp.feature.auth.domain.AuthRepository
import ru.aza1rat.marketsimapp.feature.auth.domain.model.AuthError
import ru.aza1rat.marketsimapp.feature.auth.domain.model.UserInfo
import ru.aza1rat.marketsimapp.feature.auth.domain.validaton.isLoginValid
import ru.aza1rat.marketsimapp.feature.auth.domain.validaton.isPasswordValid

class AuthRepositoryImpl @Inject constructor(
    private val client: AuthNetworkClient,
    private val sessionManager: SessionManager
) : AuthRepository {
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

                val user = try {
                    resultDto?.toUserInfoDomain()
                } catch (e: IllegalArgumentException) {
                    return Result.Error<UserInfo, AuthError>(AuthError.Unknown(e.message))
                }

                if (user == null) {
                    val code = result.statusCode
                    val message = resultResponseDto?.message
                    Result.Error<UserInfo, AuthError>(code.toError(message))
                } else {
                    setSession(resultDto!!)?.let { return it }

                    Result.Success<UserInfo, AuthError>(user)
                }
            }

            is NetworkResult.HttpError -> {
                val code = result.statusCode
                val errorsList = result.response.errors?.toErrorMessages()
                val error = errorsList.orEmpty().firstOrNull()?.let { code.toError(it) }
                if (error != null) {
                    Result.Error<UserInfo, AuthError>(error)
                } else {
                    Result.Error<UserInfo, AuthError>(AuthError.Unknown(errorsList.toErrorsString()))
                }
            }

            NetworkResult.AuthorizationRequired -> Result.Error<UserInfo, AuthError>(AuthError.Unauthorized)
            NetworkResult.ConnectionProblems -> Result.Error<UserInfo, AuthError>(AuthError.NetworkError.CONNECTION)
            NetworkResult.SerializationError -> Result.Error<UserInfo, AuthError>(AuthError.SerializationError)
            NetworkResult.Timeout -> Result.Error<UserInfo, AuthError>(AuthError.NetworkError.REQUEST_TIME_OUT)
        }
    }

    @Suppress("TooGenericExceptionCaught")
    private suspend fun setSession(loginResponseDto: LoginResponseDto): Result.Error<UserInfo, AuthError>? {
        val session = try {
            loginResponseDto.toSession()
        } catch (e: IllegalArgumentException) {
            return Result.Error<UserInfo, AuthError>(AuthError.Unknown(e.message))
        }

        return try {
            session.let {
                sessionManager.saveSession(it)
            }
            null
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.Error<UserInfo, AuthError>(AuthError.Unknown(e.message))
        }
    }
}
