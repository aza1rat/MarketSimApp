package ru.aza1rat.marketsimapp.feature.auth.login.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.aza1rat.marketsimapp.core.di.IoDispatcher
import ru.aza1rat.marketsimapp.core.domain.Result
import ru.aza1rat.marketsimapp.core.mvi.MviViewModel
import ru.aza1rat.marketsimapp.core.ui.text.UiText
import ru.aza1rat.marketsimapp.feature.auth.data.mapper.normalizeLogin
import ru.aza1rat.marketsimapp.feature.auth.domain.model.AuthError
import ru.aza1rat.marketsimapp.feature.auth.domain.model.CredentialsUser
import ru.aza1rat.marketsimapp.feature.auth.domain.model.UserInfo
import ru.aza1rat.marketsimapp.feature.auth.domain.validaton.isLoginValid
import ru.aza1rat.marketsimapp.feature.auth.domain.validaton.isPasswordValid
import ru.aza1rat.marketsimapp.feature.auth.login.domain.LoginUserAuthUseCase
import ru.aza1rat.marketsimapp.feature.auth.presentation.asUiText

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserAuthUseCase: LoginUserAuthUseCase,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MviViewModel<LoginIntent, LoginState, LoginEffect>(
    LoginState()
) {
    private val loginValidationFlow = MutableStateFlow("")
    private val passwordValidationFlow = MutableStateFlow("")

    init {
        viewModelScope.launch {
            loginValidationFlow.mapLatest { validateLogin(it) }.collect { validationResult ->
                updateState {
                    it.copy(
                        loginValidated = validationResult.isValidated,
                        loginError = validationResult.error
                    )
                }
            }
        }
        viewModelScope.launch {
            passwordValidationFlow.mapLatest { validatePassword(it) }.collect { validationResult ->
                updateState {
                    it.copy(
                        passwordValidated = validationResult.isValidated,
                        passwordError = validationResult.error
                    )
                }
            }
        }
    }

    private fun validatePassword(password: String): ValidationResult {
        val passwordValidated = password.isPasswordValid()
        val error = if (passwordValidated || password.isEmpty()) {
            null
        } else {
            AuthError.ValidationError.INVALID_PASSWORD.asUiText()
        }
        return ValidationResult(
            isValidated = passwordValidated,
            error = error
        )
    }

    private fun validateLogin(login: String): ValidationResult {
        val loginValidated = login.normalizeLogin().isLoginValid()
        val error = if (loginValidated || login.isEmpty()) {
            null
        } else {
            AuthError.ValidationError.INVALID_LOGIN.asUiText()
        }
        return ValidationResult(
            isValidated = loginValidated,
            error = error
        )
    }

    public override fun dispatch(
        intent: LoginIntent
    ) {
        when (intent) {
            is LoginIntent.LoginChanged -> loginValidationFlow.value = intent.login

            is LoginIntent.PasswordChanged -> passwordValidationFlow.value = intent.password

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
        if (state.value.isLoading || !state.value.isSubmitEnabled) return

        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }

            val loginTrimmed = login.trim()
            val userCredentials = CredentialsUser(
                email = loginTrimmed,
                password = password
            )
            withContext(ioDispatcher) {
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

    private data class ValidationResult(val isValidated: Boolean, val error: UiText?)
}
