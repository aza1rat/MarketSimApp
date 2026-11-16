package ru.aza1rat.marketsimapp.feature.auth.register.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import ru.aza1rat.marketsimapp.core.ui.element.TopNavigationBar
import ru.aza1rat.marketsimapp.core.ui.element.button.PrimaryAuthButton
import ru.aza1rat.marketsimapp.core.ui.element.textfield.AuthOutlinedTextField
import ru.aza1rat.marketsimapp.core.ui.element.textfield.PasswordTextField
import ru.aza1rat.marketsimapp.core.ui.text.UiText
import ru.aza1rat.marketsimapp.core.ui.theme.MarketSimTheme
import ru.aza1rat.marketsimapp.feature.auth.R
import ru.aza1rat.marketsimapp.feature.auth.register.presentation.RegisterIntent
import ru.aza1rat.marketsimapp.feature.auth.register.presentation.RegisterState
import ru.aza1rat.marketsimapp.feature.auth.ui.AuthBackground
import ru.aza1rat.marketsimapp.feature.auth.ui.AuthLogo
import ru.aza1rat.marketsimapp.feature.auth.ui.AuthPanelBackground
import ru.aza1rat.marketsimapp.feature.auth.ui.AuthTitle
import ru.aza1rat.marketsimapp.feature.auth.ui.authButton
import ru.aza1rat.marketsimapp.feature.auth.ui.authHorizontalPanelBackground
import ru.aza1rat.marketsimapp.feature.auth.ui.authHorizontalPanelFillWidth
import ru.aza1rat.marketsimapp.feature.auth.ui.authPanelBackground
import ru.aza1rat.marketsimapp.feature.auth.ui.authPasswordTextField
import ru.aza1rat.marketsimapp.feature.auth.ui.authTextField
import ru.aza1rat.marketsimapp.feature.auth.ui.getAuthPanelWidthFraction
import ru.aza1rat.marketsimapp.feature.auth.ui.registerPasswordTextField
import ru.aza1rat.marketsimapp.feature.auth.ui.registerTextField

@Composable
fun RegisterScreen(
    state: RegisterState,
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,
    loginTextFieldState: TextFieldState = rememberTextFieldState(),
    passwordTextFieldState: TextFieldState = rememberTextFieldState(),
    passwordConfirmationTextFieldState: TextFieldState = rememberTextFieldState(),
    onIntent: (RegisterIntent) -> Unit
) {
    val widthFraction = getAuthPanelWidthFraction(windowSizeClass)
    val registerFields =
        remember(loginTextFieldState, passwordTextFieldState, passwordConfirmationTextFieldState) {
            RegisterFields(
                loginTextFieldState,
                passwordTextFieldState,
                passwordConfirmationTextFieldState
            )
        }
    MarketSimTheme {
        AuthBackground(windowSizeClass = windowSizeClass, modifier = modifier, verticalContent = {
            VerticalContent(
                widthFraction = widthFraction,
                state = state,
                onIntent = onIntent,
                registerFields = registerFields
            )
        }, dividedHorizontalContent = {
            DividedHorizontalContent(
                state = state,
                onIntent = onIntent,
                registerFields = registerFields
            )
        })
    }
}

@Composable
private fun BoxScope.VerticalContent(
    widthFraction: Float,
    state: RegisterState,
    onIntent: (RegisterIntent) -> Unit,
    registerFields: RegisterFields
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // navigation
        AuthLogo(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = MarketSimTheme.dimens.spacingExtraSmall
                )
        )
        AuthTitle(
            text = stringResource(R.string.register_welcome_message),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MarketSimTheme.dimens.spacingExtraSmall)
        )
        Text(
            text = stringResource(R.string.register_welcome_submessage),
            modifier = Modifier.fillMaxWidth(),
            style = MarketSimTheme.typography.bodySmallMultiline,
            color = MarketSimTheme.colors.onSecondaryContainer,
            textAlign = TextAlign.Center
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
            RegisterPanel(
                state = state,
                onIntent = onIntent,
                registerFields = registerFields
            )
        }
    )
}

@Composable
private fun DividedHorizontalContent(
    state: RegisterState,
    onIntent: (RegisterIntent) -> Unit,
    registerFields: RegisterFields
) {
    Row {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .authHorizontalPanelFillWidth()
        ) {
            // navigation
            AuthLogo(modifier = Modifier.fillMaxWidth())
            AuthTitle(
                text = stringResource(R.string.register_welcome_message),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MarketSimTheme.dimens.spacingExtraSmall)
            )
            Text(
                text = stringResource(R.string.register_welcome_submessage),
                modifier = Modifier.fillMaxWidth(),
                style = MarketSimTheme.typography.bodySmallMultiline,
                color = MarketSimTheme.colors.onSecondaryContainer,
                textAlign = TextAlign.Center
            )
        }
        AuthPanelBackground(
            modifier = Modifier.authHorizontalPanelBackground(
                scrollState = rememberScrollState(),
                backgroundColor = MarketSimTheme.colors.surface
            ),
            verticalArrangement = Arrangement.Center,
            content = {
                RegisterPanel(
                    state = state,
                    onIntent = onIntent,
                    registerFields = registerFields
                )
            }
        )
    }
}

@Composable
private fun ColumnScope.RegisterPanel(
    state: RegisterState,
    onIntent: (RegisterIntent) -> Unit,
    registerFields: RegisterFields
) {
    TextFieldsInputCollection(
        loginTextFieldState = registerFields.loginTextFieldState,
        passwordTextFieldState = registerFields.passwordTextFieldState,
        passwordConfirmationTextFieldState = registerFields.passwordConfirmationTextFieldState,
        onIntent = onIntent
    )
    AuthOutlinedTextField(
        textFieldState = registerFields.loginTextFieldState,
        placeholderText = stringResource(R.string.login_input_placeholder),
        modifier = Modifier.authTextField(bottomPadding = MarketSimTheme.dimens.spacingMedium),
        enabled = !state.isLoading,
        errorText = state.loginError?.asString(),
        imeAction = ImeAction.Next
    )
    PasswordTextField(
        textFieldState = registerFields.passwordTextFieldState,
        placeholderText = stringResource(R.string.login_password_placeholder),
        modifier = Modifier.authPasswordTextField(bottomPadding = MarketSimTheme.dimens.spacingMedium),
        enabled = !state.isLoading,
        errorText = state.passwordError?.asString(),
        imeAction = ImeAction.Next
    )
    PasswordTextField(
        textFieldState = registerFields.passwordConfirmationTextFieldState,
        placeholderText = stringResource(R.string.login_password_confirmation_placeholder),
        modifier = Modifier.padding(bottom = MarketSimTheme.dimens.spacingMediumPlus),
        enabled = !state.isLoading,
        errorText = state.confirmPasswordError?.asString(),
        imeAction = ImeAction.Done,
        onKeyboardAction = {
            if (state.isSubmitEnabled) {
                onSubmitAction(
                    registerFields.loginTextFieldState,
                    registerFields.passwordTextFieldState,
                    onIntent
                )
            }
        }
    )
    CheckBoxWithText(
        checked = state.isAgreementChecked,
        enabled = !state.isLoading,
        onCheckedChange = { onIntent.invoke(RegisterIntent.AgreementChecked(it)) }
    )
    PrimaryAuthButton(
        text = stringResource(R.string.register_button),
        enabled = state.isSubmitEnabled,
        isLoading = state.isLoading,
        modifier = Modifier.authButton(paddingTop = MarketSimTheme.dimens.spacingExtraLarge),
        onClick = {
            onSubmitAction(
                registerFields.loginTextFieldState,
                registerFields.passwordTextFieldState,
                onIntent
            )
        }
    )
}

@Composable
private fun TextFieldsInputCollection(
    loginTextFieldState: TextFieldState,
    passwordTextFieldState: TextFieldState,
    passwordConfirmationTextFieldState: TextFieldState,
    onIntent: (RegisterIntent) -> Unit
) {
    LaunchedEffect(loginTextFieldState) {
        snapshotFlow { loginTextFieldState.text }.collect { currentText ->
            onIntent.invoke(
                RegisterIntent.LoginChanged(currentText.toString())
            )
        }
    }
    LaunchedEffect(passwordTextFieldState) {
        snapshotFlow { passwordTextFieldState.text }.collect { currentText ->
            onIntent.invoke(
                RegisterIntent.PasswordChanged(
                    password = currentText.toString(),
                    confirmPassword = passwordConfirmationTextFieldState.text.toString()
                )
            )
        }
    }
    LaunchedEffect(passwordConfirmationTextFieldState) {
        snapshotFlow { passwordConfirmationTextFieldState.text }.collect { currentText ->
            onIntent.invoke(
                RegisterIntent.PasswordChanged(
                    password = passwordTextFieldState.text.toString(),
                    confirmPassword = currentText.toString()
                )
            )
        }
    }
}


@Composable
private fun CheckBoxWithText(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Row(modifier = modifier) {
        RoundedCheckBox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            modifier = Modifier
                .size(MarketSimTheme.dimens.iconButtonSmallSize)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = stringResource(R.string.register_agreement_checkbox_label),
            style = MarketSimTheme.typography.labelLarge,
            color = MarketSimTheme.colors.onSurface,
            modifier = Modifier.padding(start = MarketSimTheme.dimens.spacingExtraSmall)
        )
    }
}

@Composable
private fun RoundedCheckBox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Box(
        modifier = modifier
            .minimumInteractiveComponentSize()
            .clip(CircleShape)
            .background(
                color = if (checked) MarketSimTheme.colors.primary else Color.Transparent
            )
            .border(
                width = if (checked) 0.dp else MarketSimTheme.dimens.borderWidth,
                color = MarketSimTheme.colors.onSecondaryContainer,
                shape = CircleShape
            )
            .toggleable(
                value = checked,
                onValueChange = {
                    if (enabled) {
                        onCheckedChange.invoke(it)
                    }
                },
                role = Role.Checkbox
            )
    ) {
        if (checked) {
            Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = "",
            )
        }
    }
}

