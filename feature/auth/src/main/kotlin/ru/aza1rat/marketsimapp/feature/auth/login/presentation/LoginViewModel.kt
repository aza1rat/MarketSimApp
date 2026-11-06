package ru.aza1rat.marketsimapp.feature.auth.login.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.aza1rat.marketsimapp.core.domain.Result
import ru.aza1rat.marketsimapp.core.mvi.MviViewModel
import ru.aza1rat.marketsimapp.feature.auth.data.mapper.normalizeLogin
import ru.aza1rat.marketsimapp.feature.auth.domain.model.AuthError
import ru.aza1rat.marketsimapp.feature.auth.domain.model.CredentialsUser
import ru.aza1rat.marketsimapp.feature.auth.domain.model.UserInfo
import ru.aza1rat.marketsimapp.feature.auth.domain.validaton.isLoginValid
import ru.aza1rat.marketsimapp.feature.auth.domain.validaton.isPasswordValid
import ru.aza1rat.marketsimapp.feature.auth.login.domain.LoginUserAuthUseCase
import ru.aza1rat.marketsimapp.feature.auth.presentation.asUiText

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUserAuthUseCase: LoginUserAuthUseCase) :
    MviViewModel<LoginIntent, LoginState, LoginEffect>(
        LoginState()
    ) {
    private val loginValidationChannel = Channel<String>(Channel.CONFLATED)
    private val passwordValidationChannel = Channel<String>(Channel.CONFLATED)

    init {
        viewModelScope.launch {
            loginValidationChannel.receiveAsFlow().collectLatest {
                validateLogin(it)
            }
            passwordValidationChannel.receiveAsFlow().collectLatest {
                validatePassword(it)
            }
        }
    }

    private fun validatePassword(password: String) {
        val passwordValidated = password.isPasswordValid()
        val error = if (passwordValidated) {
            null
        } else {
            AuthError.ValidationError.INVALID_PASSWORD.asUiText()
        }
        updateState {
            it.copy(
                passwordError = error,
                passwordValidated = passwordValidated
            )
        }
    }

    private fun validateLogin(login: String) {
        val loginValidated = login.normalizeLogin().isLoginValid()
        val error = if (loginValidated) {
            null
        } else {
            AuthError.ValidationError.INVALID_LOGIN.asUiText()
        }
        updateState {
            it.copy(
                loginError = error,
                loginValidated = loginValidated
            )
        }
    }

    override fun dispatch(
        intent: LoginIntent
    ) {
        when (intent) {
            is LoginIntent.LoginChanged -> loginValidationChannel.trySend(intent.login)

            is LoginIntent.PasswordChanged -> passwordValidationChannel.trySend(intent.password)

            is LoginIntent.SubmitClicked -> loginUser(intent.login, intent.password)
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

    private fun loginUser(login: String, password: String) {
        if (state.value.isLoading) return

        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }

            val loginTrimmed = login.trim()
            val userCredentials = CredentialsUser(
                email = loginTrimmed,
                password = password
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
