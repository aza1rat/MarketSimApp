package ru.aza1rat.marketsimapp.feature.auth.login.presentation

import ru.aza1rat.marketsimapp.core.mvi.MviState
import ru.aza1rat.marketsimapp.core.ui.text.UiText
import ru.aza1rat.marketsimapp.feature.auth.data.mapper.normalizeLogin
import ru.aza1rat.marketsimapp.feature.auth.domain.validaton.isLoginValid
import ru.aza1rat.marketsimapp.feature.auth.domain.validaton.isPasswordValid

data class LoginState(
    val login: String = "",
    val password: String = "",
    val loginError: UiText? = null,
    val passwordError: UiText? = null,
    val isLoading: Boolean = false
) : MviState {
    val isSubmitEnabled: Boolean
        get() = login.normalizeLogin().isLoginValid() && password.isPasswordValid()
}
