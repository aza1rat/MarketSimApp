package ru.aza1rat.marketsimapp.feature.auth.register.presentation

import ru.aza1rat.marketsimapp.core.mvi.MviState
import ru.aza1rat.marketsimapp.core.ui.text.UiText
import ru.aza1rat.marketsimapp.feature.auth.data.mapper.normalizeLogin
import ru.aza1rat.marketsimapp.feature.auth.domain.validaton.isLoginValid
import ru.aza1rat.marketsimapp.feature.auth.domain.validaton.isPasswordValid

data class RegisterState(
    val login: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val loginError: UiText? = null,
    val passwordError: UiText? = null,
    val confirmPasswordError: UiText? = null,
    val isAgreementChecked: Boolean = false,
    val isLoading: Boolean = false
) : MviState {
    val arePasswordsEqual: Boolean
        get() = password == confirmPassword
    val isSubmitEnabled: Boolean
        get() = login.normalizeLogin()
            .isLoginValid() && password.isPasswordValid() && arePasswordsEqual && isAgreementChecked
}
