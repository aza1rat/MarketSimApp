package ru.aza1rat.marketsimapp.feature.auth.login.presentation

import ru.aza1rat.marketsimapp.core.mvi.MviEffect
import ru.aza1rat.marketsimapp.core.ui.text.UiText

sealed class LoginEffect : MviEffect {
    data object NavigateToRegister : LoginEffect()
    data object NavigateToMain : LoginEffect()
    data class ShowError(val message: UiText? = null) : LoginEffect()
}
