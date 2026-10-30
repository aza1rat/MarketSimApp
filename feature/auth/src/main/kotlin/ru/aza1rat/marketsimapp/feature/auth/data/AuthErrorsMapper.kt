package ru.aza1rat.marketsimapp.feature.auth.data

import ru.aza1rat.marketsimapp.core.network.codes.BAD_REQUEST_ERROR
import ru.aza1rat.marketsimapp.core.network.codes.SERVER_ERROR
import ru.aza1rat.marketsimapp.core.network.codes.UNAUTHORIZED_ERROR
import ru.aza1rat.marketsimapp.feature.auth.domain.model.AuthError

fun Int.toError(errorMessage: String? = null): AuthError {
    return when {
        this == BAD_REQUEST_ERROR && errorMessage == USER_EXISTS_MESSAGE ->
            AuthError.CredentialsError.USER_ALREADY_EXISTS
        this == BAD_REQUEST_ERROR && errorMessage == INVALID_CREDENTIALS ->
            AuthError.CredentialsError.INVALID_CREDENTIALS
        this == UNAUTHORIZED_ERROR -> AuthError.Unauthorized
        this in SERVER_ERROR -> AuthError.ServerError
        else -> AuthError.Unknown(errorMessage)
    }
}

private const val USER_EXISTS_MESSAGE = "Email already exists"
private const val INVALID_CREDENTIALS = "Invalid email or password"
