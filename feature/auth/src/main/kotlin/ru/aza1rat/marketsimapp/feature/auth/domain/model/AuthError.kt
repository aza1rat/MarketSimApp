package ru.aza1rat.marketsimapp.feature.auth.domain.model

sealed interface AuthError {
    data object InvalidEmail : AuthError
    data object WeakPassword : AuthError
    data object LongPassword : AuthError
    data object InvalidCredentials : AuthError
    data object UserAlreadyExists : AuthError
    data object Unauthorized : AuthError
    data object ServerError : AuthError
    data object NetworkError : AuthError
    data object Unknown : AuthError
}
