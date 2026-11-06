package ru.aza1rat.marketsimapp.feature.auth.register.presentation

import androidx.compose.runtime.Immutable
import ru.aza1rat.marketsimapp.core.mvi.MviState
import ru.aza1rat.marketsimapp.core.ui.text.UiText

@Immutable
data class RegisterState(
    val loginError: UiText? = null,
    val passwordError: UiText? = null,
    val confirmPasswordError: UiText? = null,
    val isAgreementChecked: Boolean = false,
    val isLoading: Boolean = false,
    val loginValidated: Boolean = false,
    val passwordValidated: Boolean = false,
    val arePasswordEqual: Boolean = false,
    val isSubmitEnabled: Boolean =
        loginValidated && passwordValidated && arePasswordEqual && isAgreementChecked
) : MviState
