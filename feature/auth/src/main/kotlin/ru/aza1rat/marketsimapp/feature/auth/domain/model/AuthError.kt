package ru.aza1rat.marketsimapp.feature.auth.domain.model

import ru.aza1rat.marketsimapp.core.domain.RootError

sealed interface AuthError : RootError {
    enum class CredentialsError : AuthError {
        INVALID_CREDENTIALS,
        USER_ALREADY_EXISTS
    }

    enum class ValidationError : AuthError {
        INVALID_PASSWORD,
        INVALID_LOGIN,
    }

    enum class NetworkError : AuthError {
        REQUEST_TIME_OUT,
        CONNECTION
    }

    data object SerializationError : AuthError
    data object Unauthorized : AuthError
    data object ServerError : AuthError
    data class Unknown(val message: String?) : AuthError
}
