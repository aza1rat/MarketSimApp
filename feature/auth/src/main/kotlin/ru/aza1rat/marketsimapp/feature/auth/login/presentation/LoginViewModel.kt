package ru.aza1rat.marketsimapp.feature.auth.login.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import ru.aza1rat.marketsimapp.core.domain.Result
import ru.aza1rat.marketsimapp.core.mvi.MviViewModel
import ru.aza1rat.marketsimapp.feature.auth.domain.model.AuthError
import ru.aza1rat.marketsimapp.feature.auth.domain.model.CredentialsUser
import ru.aza1rat.marketsimapp.feature.auth.domain.model.UserInfo
import ru.aza1rat.marketsimapp.feature.auth.login.domain.LoginUserAuthUseCase
import ru.aza1rat.marketsimapp.feature.auth.presentation.asUiText

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUserAuthUseCase: LoginUserAuthUseCase) :
    MviViewModel<LoginIntent, LoginState, LoginEffect>(
        LoginState()
    ) {
    override fun dispatch(
        intent: LoginIntent
    ) {
        when (intent) {
            is LoginIntent.LoginChanged -> updateState {
                it.copy(
                    login = intent.login,
                    loginError = null
                )
            }

            is LoginIntent.PasswordChanged -> updateState {
                it.copy(
                    password = intent.password,
                    passwordError = null
                )
            }

            LoginIntent.SubmitClicked -> loginUser()
            LoginIntent.RegisterClicked -> handleIntent(intent)
        }
    }

    private fun handleIntent(intent: LoginIntent) {
        viewModelScope.launch {
            when (intent) {
                LoginIntent.RegisterClicked -> emitEffect(LoginEffect.NavigateToRegister)
                else -> Unit
            }
        }
    }

    private fun loginUser() {
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
                when (val res = loginUserAuthUseCase.invoke(userCredentials)) {
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

                        else -> emitEffect(LoginEffect.ShowError(error.asUiText()))
                    }

                    is Result.Success<UserInfo, AuthError> -> emitEffect(LoginEffect.NavigateToMain)
                }
            } finally {
                updateState { it.copy(isLoading = false) }
            }
        }
    }
}
