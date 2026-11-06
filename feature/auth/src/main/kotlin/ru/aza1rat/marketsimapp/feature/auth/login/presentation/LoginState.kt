package ru.aza1rat.marketsimapp.feature.auth.login.presentation

import androidx.compose.runtime.Immutable
import ru.aza1rat.marketsimapp.core.mvi.MviState
import ru.aza1rat.marketsimapp.core.ui.text.UiText

@Immutable
data class LoginState(
    val loginError: UiText? = null,
    val passwordError: UiText? = null,
    val isLoading: Boolean = false,
    val loginValidated: Boolean = false,
    val passwordValidated: Boolean = false,
    val isSubmitEnabled: Boolean = loginValidated && passwordValidated
) : MviState
