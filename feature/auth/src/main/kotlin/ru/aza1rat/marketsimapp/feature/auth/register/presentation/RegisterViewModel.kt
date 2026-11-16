package ru.aza1rat.marketsimapp.feature.auth.register.presentation

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
import ru.aza1rat.marketsimapp.feature.auth.R
import ru.aza1rat.marketsimapp.feature.auth.data.mapper.normalizeLogin
import ru.aza1rat.marketsimapp.feature.auth.domain.model.AuthError
import ru.aza1rat.marketsimapp.feature.auth.domain.model.CredentialsUser
import ru.aza1rat.marketsimapp.feature.auth.domain.model.UserInfo
import ru.aza1rat.marketsimapp.feature.auth.domain.validaton.isLoginValid
import ru.aza1rat.marketsimapp.feature.auth.domain.validaton.isPasswordValid
import ru.aza1rat.marketsimapp.feature.auth.presentation.UiValidationResult
import ru.aza1rat.marketsimapp.feature.auth.presentation.asUiText
import ru.aza1rat.marketsimapp.feature.auth.register.domain.RegisterUserAuthUseCase

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserAuthUseCase: RegisterUserAuthUseCase,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MviViewModel<RegisterIntent, RegisterState, RegisterEffect>(
    RegisterState()
) {
    private val loginValidationFlow = MutableStateFlow("")
    private val passwordValidationFlow = MutableStateFlow(RegisterPasswordIntent())

    init {
        viewModelScope.launch {
            loginValidationFlow.mapLatest { validateLogin(it) }.collect { loginValidationResult ->
                updateState {
                    it.copy(
                        loginError = loginValidationResult.error,
                        loginValidated = loginValidationResult.isValidated
                    )
                }
            }
        }
        viewModelScope.launch {
            passwordValidationFlow.mapLatest { validatePassword(it) }
                .collect { passwordValidationResult ->
                    updateState {
                        it.copy(
                            passwordError = passwordValidationResult.passwordError,
                            confirmPasswordError = passwordValidationResult.confirmationPasswordError,
                            passwordValidated = passwordValidationResult.passwordValidated,
                            arePasswordEqual = passwordValidationResult.confirmationPasswordValidated
                        )
                    }
                }
        }
    }

    private fun validatePassword(registerPasswordIntent: RegisterPasswordIntent): UiValidationPasswordResult {
        val passwordValidated = registerPasswordIntent.password.isPasswordValid()
        val confirmationValidated =
            registerPasswordIntent.password == registerPasswordIntent.confirmationPassword
        val error = if (passwordValidated || registerPasswordIntent.password.isEmpty()) {
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
        return UiValidationPasswordResult(
            passwordValidated = passwordValidated,
            confirmationPasswordValidated = confirmationValidated,
            passwordError = error,
            confirmationPasswordError = confirmationError
        )
    }

    private fun validateLogin(login: String): UiValidationResult {
        val loginValidated = login.normalizeLogin().isLoginValid()
        val error = if (loginValidated || login.isEmpty()) {
            null
        } else {
            AuthError.ValidationError.INVALID_LOGIN.asUiText()
        }
        return UiValidationResult(
            isValidated = loginValidated,
            error = error
        )
    }

    public override fun dispatch(
        intent: RegisterIntent
    ) {
        when (intent) {
            is RegisterIntent.LoginChanged -> loginValidationFlow.value = intent.login

            is RegisterIntent.PasswordChanged ->
                passwordValidationFlow.value =
                    RegisterPasswordIntent(
                        password = intent.password,
                        confirmationPassword = intent.confirmPassword
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

    private data class RegisterPasswordIntent(
        val password: String = "",
        val confirmationPassword: String = "",
    )

    private data class UiValidationPasswordResult(
        val passwordValidated: Boolean,
        val confirmationPasswordValidated: Boolean,
        val passwordError: UiText?,
        val confirmationPasswordError: UiText?
    )
}
