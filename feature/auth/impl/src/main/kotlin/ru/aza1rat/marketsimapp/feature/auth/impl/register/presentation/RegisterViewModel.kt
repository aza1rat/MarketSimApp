package ru.aza1rat.marketsimapp.feature.auth.impl.register.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import ru.aza1rat.marketsimapp.core.mvi.MviViewModel
import ru.aza1rat.marketsimapp.feature.auth.impl.register.domain.RegisterUserAuthUseCase

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUserAuthUseCase: RegisterUserAuthUseCase) :
    MviViewModel<RegisterIntent, RegisterState, RegisterEffect>(
        RegisterState()
    ) {
    override fun dispatch(intent: RegisterIntent) {
    }
}