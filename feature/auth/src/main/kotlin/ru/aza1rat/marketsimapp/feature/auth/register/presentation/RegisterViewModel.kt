package ru.aza1rat.marketsimapp.feature.auth.register.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.aza1rat.marketsimapp.core.domain.Result
import ru.aza1rat.marketsimapp.core.mvi.MviViewModel
import ru.aza1rat.marketsimapp.core.ui.text.UiText
import ru.aza1rat.marketsimapp.feature.auth.R
import ru.aza1rat.marketsimapp.feature.auth.data.mapper.normalizeLogin
import ru.aza1rat.marketsimapp.feature.auth.domain.model.AuthError
import ru.aza1rat.marketsimapp.feature.auth.domain.model.CredentialsUser
import ru.aza1rat.marketsimapp.feature.auth.domain.model.UserInfo
import ru.aza1rat.marketsimapp.feature.auth.domain.validaton.isLoginValid
import ru.aza1rat.marketsimapp.feature.auth.domain.validaton.isPasswordValid
import ru.aza1rat.marketsimapp.feature.auth.presentation.asUiText
import ru.aza1rat.marketsimapp.feature.auth.register.domain.RegisterUserAuthUseCase

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUserAuthUseCase: RegisterUserAuthUseCase) :
    MviViewModel<RegisterIntent, RegisterState, RegisterEffect>(
        RegisterState()
    ) {

    private val loginValidationChannel = Channel<String>(Channel.CONFLATED)
    private val passwordValidationChannel = Channel<RegisterPasswordIntent>(Channel.CONFLATED)

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

    private fun validatePassword(registerPasswordIntent: RegisterPasswordIntent) {
        val passwordValidated = registerPasswordIntent.password.isPasswordValid()
        val confirmationValidated = registerPasswordIntent.password == registerPasswordIntent.confirmationPassword
        val error = if (passwordValidated) {
            null
        } else {
            AuthError.ValidationError.INVALID_PASSWORD.asUiText()
        }
        val confirmationError = if (error == null && !confirmationValidated) {
            UiText.StringResource(
                R.string.error_invalid_confirm_password
            )
        } else {
            null
        }
        updateState {
            it.copy(
                passwordError = error,
                confirmPasswordError = confirmationError,
                passwordValidated = passwordValidated,
                arePasswordEqual = confirmationValidated
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
        intent: RegisterIntent
    ) {
        when (intent) {
            is RegisterIntent.LoginChanged -> loginValidationChannel.trySend(intent.login)

            is RegisterIntent.PasswordChanged -> passwordValidationChannel.trySend(
                RegisterPasswordIntent(
                    password = intent.password,
                    confirmationPassword = intent.confirmPassword
                )
            )

            is RegisterIntent.AgreementChecked -> updateState {
                it.copy(
                    isAgreementChecked = intent.checked,
                )
            }

            RegisterIntent.BackClicked -> handleIntent(intent)

            is RegisterIntent.SubmitClicked -> registerUser(
                login = intent.login,
                password = intent.password
            )
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

    private fun registerUser(login: String, password: String) {
        if (state.value.isLoading) return

        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }

            val loginTrimmed = login.trim()
            val userCredentials = CredentialsUser(
                email = loginTrimmed,
                password = password
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

    private data class RegisterPasswordIntent(
        val password: String,
        val confirmationPassword: String,
    )
}
