package ru.aza1rat.marketsimapp.feature.auth.register.presentation

import ru.aza1rat.marketsimapp.core.mvi.MviIntent

sealed class RegisterIntent : MviIntent {
    data class LoginChanged(val login: String) : RegisterIntent()
    data class PasswordChanged(val password: String) : RegisterIntent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterIntent()
    data object ConfirmPasswordUnfocused : RegisterIntent()
    data class AgreementChecked(val checked: Boolean = false) : RegisterIntent()
    data object SubmitClicked : RegisterIntent()
    data object BackClicked : RegisterIntent()
}
