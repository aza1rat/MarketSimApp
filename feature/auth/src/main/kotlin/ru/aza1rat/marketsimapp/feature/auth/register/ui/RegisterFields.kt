package ru.aza1rat.marketsimapp.feature.auth.register.ui

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Stable

@Stable
data class RegisterFields(
    val loginTextFieldState: TextFieldState,
    val passwordTextFieldState: TextFieldState,
    val passwordConfirmationTextFieldState: TextFieldState
)
