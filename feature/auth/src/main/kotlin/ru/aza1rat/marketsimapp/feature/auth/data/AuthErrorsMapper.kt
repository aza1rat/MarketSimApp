package ru.aza1rat.marketsimapp.feature.auth.data

import ru.aza1rat.marketsimapp.core.network.codes.BAD_REQUEST_ERROR
import ru.aza1rat.marketsimapp.core.network.codes.SERVER_ERROR
import ru.aza1rat.marketsimapp.core.network.codes.UNAUTHORIZED_ERROR
import ru.aza1rat.marketsimapp.feature.auth.domain.model.AuthError

fun Int.toError(errorMessage: String? = null): AuthError {
    return when {
        this == BAD_REQUEST_ERROR && errorMessage == USER_EXISTS_MESSAGE -> AuthError.UserAlreadyExists
        this == BAD_REQUEST_ERROR && errorMessage == INVALID_CREDENTIALS -> AuthError.InvalidCredentials
        this == UNAUTHORIZED_ERROR -> AuthError.Unauthorized
        this >= SERVER_ERROR -> AuthError.ServerError
        else -> AuthError.Unknown
    }
}

private const val USER_EXISTS_MESSAGE = "Email already exists"
private const val INVALID_CREDENTIALS = "Invalid email or password"
