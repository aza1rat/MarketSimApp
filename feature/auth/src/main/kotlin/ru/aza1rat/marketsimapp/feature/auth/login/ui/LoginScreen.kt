package ru.aza1rat.marketsimapp.feature.auth.login.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.window.core.layout.WindowSizeClass
import ru.aza1rat.marketsimapp.core.ui.element.button.PrimaryAuthButton
import ru.aza1rat.marketsimapp.core.ui.element.textfield.AuthOutlinedTextField
import ru.aza1rat.marketsimapp.core.ui.element.textfield.PasswordTextField
import ru.aza1rat.marketsimapp.core.ui.text.UiText
import ru.aza1rat.marketsimapp.core.ui.theme.MarketSimTheme
import ru.aza1rat.marketsimapp.feature.auth.R
import ru.aza1rat.marketsimapp.feature.auth.login.presentation.LoginIntent
import ru.aza1rat.marketsimapp.feature.auth.login.presentation.LoginState
import ru.aza1rat.marketsimapp.feature.auth.ui.AuthBackground
import ru.aza1rat.marketsimapp.feature.auth.ui.AuthLogo
import ru.aza1rat.marketsimapp.feature.auth.ui.AuthPanelBackground
import ru.aza1rat.marketsimapp.feature.auth.ui.AuthTextButton
import ru.aza1rat.marketsimapp.feature.auth.ui.AuthTitle
import ru.aza1rat.marketsimapp.feature.auth.ui.authButton
import ru.aza1rat.marketsimapp.feature.auth.ui.authHorizontalPanelBackground
import ru.aza1rat.marketsimapp.feature.auth.ui.authHorizontalPanelFillWidth
import ru.aza1rat.marketsimapp.feature.auth.ui.authPanelBackground
import ru.aza1rat.marketsimapp.feature.auth.ui.authPasswordTextField
import ru.aza1rat.marketsimapp.feature.auth.ui.authTextField
import ru.aza1rat.marketsimapp.feature.auth.ui.getAuthPanelWidthFraction

@Composable
fun LoginScreen(
    state: LoginState,
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,
    loginTextFieldState: TextFieldState = rememberTextFieldState(),
    passwordTextFieldState: TextFieldState = rememberTextFieldState(),
    onIntent: (LoginIntent) -> Unit
) {
    val widthFraction = getAuthPanelWidthFraction(windowSizeClass)
    MarketSimTheme {
        AuthBackground(
            windowSizeClass = windowSizeClass,
            modifier = modifier,
            verticalContent = {
                VerticalContent(
                    widthFraction = widthFraction,
                    state = state,
                    onIntent = onIntent,
                    loginTextFieldState = loginTextFieldState,
                    passwordTextFieldState = passwordTextFieldState
                )
            },
            dividedHorizontalContent = {
                DividedHorizontalContent(
                    state = state,
                    onIntent = onIntent,
                    loginTextFieldState = loginTextFieldState,
                    passwordTextFieldState = passwordTextFieldState
                )
            }
        )
    }
}

@Composable
private fun BoxScope.VerticalContent(
    widthFraction: Float,
    state: LoginState,
    onIntent: (LoginIntent) -> Unit,
    loginTextFieldState: TextFieldState,
    passwordTextFieldState: TextFieldState
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        AuthLogo(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = MarketSimTheme.dimens.edgePaddingLarge,
                    bottom = MarketSimTheme.dimens.spacingExtraSmall
                )
        )
        AuthTitle(
            text = stringResource(R.string.login_welcome_message),
            modifier = Modifier.fillMaxWidth()
        )
    }
    AuthPanelBackground(
        modifier = Modifier
            .fillMaxWidth(widthFraction)
            .align(Alignment.BottomCenter)
            .authPanelBackground(
                backgroundColor = MarketSimTheme.colors.surface,
                shape = RoundedCornerShape(
                    topStart = MarketSimTheme.dimens.edgePaddingMediumPlus,
                    topEnd = MarketSimTheme.dimens.edgePaddingMediumPlus
                )
            ),
        content = {
            AuthPanel(
                state,
                onIntent,
                loginTextFieldState,
                passwordTextFieldState
            )
        }
    )
}

@Composable
private fun DividedHorizontalContent(
    state: LoginState,
    onIntent: (LoginIntent) -> Unit,
    loginTextFieldState: TextFieldState,
    passwordTextFieldState: TextFieldState
) {
    Row {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .authHorizontalPanelFillWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            AuthLogo(modifier = Modifier.fillMaxWidth())
            AuthTitle(
                text = stringResource(R.string.login_welcome_message),
                modifier = Modifier.fillMaxWidth()
            )
        }
        AuthPanelBackground(
            modifier = Modifier.authHorizontalPanelBackground(
                state = rememberScrollState(),
                backgroundColor = MarketSimTheme.colors.surface
            ),
            verticalArrangement = Arrangement.Center,
            content = {
                AuthPanel(state, onIntent, loginTextFieldState, passwordTextFieldState)
            }
        )
    }
}

@Composable
private fun ColumnScope.AuthPanel(
    state: LoginState,
    onIntent: (LoginIntent) -> Unit,
    loginTextFieldState: TextFieldState,
    passwordTextFieldState: TextFieldState
) {
    TextFieldsInputCollection(
        loginTextFieldState = loginTextFieldState,
        passwordTextFieldState = passwordTextFieldState,
        onIntent = onIntent
    )
    AuthOutlinedTextField(
        textFieldState = loginTextFieldState,
        placeholderText = stringResource(R.string.login_input_placeholder),
        modifier = Modifier.authTextField(
            bottomPadding = MarketSimTheme.dimens.spacingMedium
        ),
        enabled = !state.isLoading,
        errorText = state.loginError?.asString(),
        imeAction = ImeAction.Next
    )
    PasswordTextField(
        textFieldState = passwordTextFieldState,
        placeholderText = stringResource(R.string.login_password_placeholder),
        modifier = Modifier.authPasswordTextField(
            bottomPadding = MarketSimTheme.dimens.spacingMediumPlus
        ),
        enabled = !state.isLoading,
        errorText = state.passwordError?.asString(),
        imeAction = ImeAction.Done,
        onKeyboardAction = {
            onPasswordInputDone(state, onIntent, loginTextFieldState, passwordTextFieldState)
        }
    )
    AuthTextButton(
        text = stringResource(R.string.login_register_navigation_button),
        onClick = { onIntent.invoke(LoginIntent.RegisterClicked) },
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )
    PrimaryAuthButton(
        text = stringResource(R.string.login_button),
        enabled = state.isSubmitEnabled,
        isLoading = state.isLoading,
        modifier = Modifier
            .authButton(paddingTop = MarketSimTheme.dimens.spacingExtraSmall),
        onClick = {
            onIntent.invoke(
                LoginIntent.SubmitClicked(
                    login = loginTextFieldState.text.toString(),
                    password = passwordTextFieldState.text.toString()
                )
            )
        }
    )
}

private fun onPasswordInputDone(
    state: LoginState,
    onIntent: (LoginIntent) -> Unit,
    loginTextFieldState: TextFieldState,
    passwordTextFieldState: TextFieldState
) {
    if (state.isSubmitEnabled) {
        onIntent.invoke(
            LoginIntent.SubmitClicked(
                login = loginTextFieldState.text.toString(),
                password = passwordTextFieldState.text.toString()
            )
        )
    }
}

@Composable
private fun TextFieldsInputCollection(
    loginTextFieldState: TextFieldState,
    passwordTextFieldState: TextFieldState,
    onIntent: (LoginIntent) -> Unit
) {
    LaunchedEffect(loginTextFieldState) {
        snapshotFlow { loginTextFieldState.text }.collect { currentText ->
            onIntent.invoke(
                LoginIntent.LoginChanged(currentText.toString())
            )
        }
    }

    LaunchedEffect(passwordTextFieldState) {
        snapshotFlow { passwordTextFieldState.text }.collect { currentText ->
            onIntent.invoke(
                LoginIntent.PasswordChanged(currentText.toString())
            )
        }
    }
}

@PreviewScreenSizes
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        state = LoginState(),
        modifier = Modifier.fillMaxSize(),
        onIntent = {}
    )
}

@PreviewLightDark
@Composable
private fun LoginScreenLightDark() {
    MarketSimTheme {
        LoginScreen(
            state = LoginState(),
            modifier = Modifier.fillMaxSize(),
            onIntent = {}
        )
    }
}

@Preview
@Composable
private fun LoginScreenValidated() {
    val loginTextField = rememberTextFieldState(initialText = "me@example.com")
    val passwordTextField = rememberTextFieldState(initialText = "password1@")
    MarketSimTheme {
        LoginScreen(
            state = LoginState(
                loginValidated = true,
                passwordValidated = true
            ),
            modifier = Modifier.fillMaxSize(),
            onIntent = {},
            loginTextFieldState = loginTextField,
            passwordTextFieldState = passwordTextField
        )
    }
}

@Preview
@Composable
private fun LoginScreenInvalidated() {
    val loginTextField = rememberTextFieldState(initialText = "me@")
    val passwordTextField = rememberTextFieldState(initialText = "pass")
    MarketSimTheme {
        LoginScreen(
            state = LoginState(
                loginValidated = false,
                passwordValidated = false,
                loginError = UiText.StringResource(R.string.error_invalid_login),
                passwordError = UiText.StringResource(R.string.error_invalid_password)
            ),
            modifier = Modifier.fillMaxSize(),
            onIntent = {},
            loginTextFieldState = loginTextField,
            passwordTextFieldState = passwordTextField
        )
    }
}
