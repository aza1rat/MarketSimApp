package ru.aza1rat.marketsimapp.feature.auth.impl.login.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import ru.aza1rat.marketsimapp.core.mvi.MviViewModel
import ru.aza1rat.marketsimapp.feature.auth.impl.login.domain.LoginUserAuthUseCase

@HiltViewModel
@Suppress("UnusedPrivateProperty")
class LoginViewModel @Inject constructor(private val loginUserAuthUseCase: LoginUserAuthUseCase) :
    MviViewModel<LoginIntent, LoginState, LoginEffect>(
        LoginState()
    ) {
    @Suppress("EmptyFunctionBlock")
    override fun dispatch(intent: LoginIntent) {
    }
}
