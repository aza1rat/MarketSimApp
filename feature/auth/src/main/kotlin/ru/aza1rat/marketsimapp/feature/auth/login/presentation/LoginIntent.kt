package ru.aza1rat.marketsimapp.feature.auth.login.presentation

import ru.aza1rat.marketsimapp.core.mvi.MviIntent

sealed class LoginIntent : MviIntent {
    data class LoginChanged(val login: String) : LoginIntent()
    data class PasswordChanged(val password: String) : LoginIntent()
    data class SubmitClicked(val login: String, val password: String) : LoginIntent()
    data object RegisterClicked : LoginIntent()
}
