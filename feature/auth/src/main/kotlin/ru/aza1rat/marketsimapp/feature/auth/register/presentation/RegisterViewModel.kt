package ru.aza1rat.marketsimapp.feature.auth.register.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import ru.aza1rat.marketsimapp.core.domain.Result
import ru.aza1rat.marketsimapp.core.mvi.MviViewModel
import ru.aza1rat.marketsimapp.core.ui.text.UiText
import ru.aza1rat.marketsimapp.feature.auth.R
import ru.aza1rat.marketsimapp.feature.auth.domain.model.AuthError
import ru.aza1rat.marketsimapp.feature.auth.domain.model.CredentialsUser
import ru.aza1rat.marketsimapp.feature.auth.domain.model.UserInfo
import ru.aza1rat.marketsimapp.feature.auth.presentation.asUiText
import ru.aza1rat.marketsimapp.feature.auth.register.domain.RegisterUserAuthUseCase

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUserAuthUseCase: RegisterUserAuthUseCase) :
    MviViewModel<RegisterIntent, RegisterState, RegisterEffect>(
        RegisterState()
    ) {

    override fun dispatch(
        intent: RegisterIntent
    ) {
        when (intent) {
            is RegisterIntent.LoginChanged -> updateState {
                it.copy(
                    login = intent.login,
                    loginError = null
                )
            }

            is RegisterIntent.PasswordChanged -> updateState {
                it.copy(
                    password = intent.password,
                    passwordError = null
                )
            }

            is RegisterIntent.ConfirmPasswordChanged -> updateState {
                it.copy(
                    confirmPassword = intent.confirmPassword,
                    confirmPasswordError = null
                )
            }

            is RegisterIntent.AgreementChecked -> updateState {
                it.copy(
                    isAgreementChecked = intent.checked,
                )
            }

            RegisterIntent.BackClicked -> handleIntent(intent)
            RegisterIntent.ConfirmPasswordUnfocused -> updateState {
                it.copy(
                    confirmPasswordError = validateConfirmPassword(it)
                )
            }

            RegisterIntent.SubmitClicked -> registerUser()
        }
    }

    private fun handleIntent(intent: RegisterIntent) {
        viewModelScope.launch {
            when (intent) {
                RegisterIntent.BackClicked -> emitEffect(RegisterEffect.NavigateToLogin)
                else -> Unit
            }
        }
    }

    private fun validateConfirmPassword(state: RegisterState): UiText? =
        if (state.arePasswordsEqual) {
            null
        } else {
            UiText.StringResource(
                R.string.error_invalid_confirm_password
            )
        }

    private fun registerUser() {
        if (state.value.isLoading) return

        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }

            val loginTrimmed = state.value.login.trim()
            val passwordTrimmed = state.value.password
            val userCredentials = CredentialsUser(
                email = loginTrimmed,
                password = passwordTrimmed
            )
            try {
                when (val res = registerUserAuthUseCase.invoke(userCredentials)) {
                    is Result.Error<UserInfo, AuthError> -> when (val error = res.error) {
                        AuthError.ValidationError.INVALID_PASSWORD -> updateState {
                            it.copy(
                                passwordError = error.asUiText()
                            )
                        }

                        AuthError.ValidationError.INVALID_LOGIN -> updateState {
                            it.copy(
                                loginError = error.asUiText()
                            )
                        }

                        else -> emitEffect(
                            RegisterEffect.ShowError(
                                error.asUiText()
                            )
                        )
                    }

                    is Result.Success<UserInfo, AuthError> -> emitEffect(RegisterEffect.NavigateToMain)
                }
            } finally {
                updateState { it.copy(isLoading = false) }
            }
        }
    }
}
