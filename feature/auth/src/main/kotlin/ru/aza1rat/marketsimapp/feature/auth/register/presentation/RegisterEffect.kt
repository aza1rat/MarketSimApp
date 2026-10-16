package ru.aza1rat.marketsimapp.feature.auth.register.presentation

import ru.aza1rat.marketsimapp.core.mvi.MviEffect

sealed class RegisterEffect : MviEffect {
    data object NavigateToLogin : RegisterEffect()
    data object NavigateToMain : RegisterEffect()
}
