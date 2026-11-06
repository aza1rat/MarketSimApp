package ru.aza1rat.marketsimapp.feature.auth.register.presentation

import ru.aza1rat.marketsimapp.core.mvi.MviIntent

sealed class RegisterIntent : MviIntent {
    data class LoginChanged(val login: String) : RegisterIntent()
    data class PasswordChanged(val password: String, val confirmPassword: String) : RegisterIntent()
    data class AgreementChecked(val checked: Boolean = false) : RegisterIntent()
    data class SubmitClicked(val login: String, val password: String) : RegisterIntent()
    data object BackClicked : RegisterIntent()
}
