package ru.aza1rat.marketsimapp.feature.auth.register.presentation

import ru.aza1rat.marketsimapp.core.mvi.MviEffect
import ru.aza1rat.marketsimapp.core.ui.text.UiText

sealed class RegisterEffect : MviEffect {
    data object NavigateToLogin : RegisterEffect()
    data object NavigateToMain : RegisterEffect()
    data class ShowError(val message: UiText? = null) : RegisterEffect()
}
