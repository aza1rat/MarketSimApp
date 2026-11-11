package ru.aza1rat.marketsimapp.core.ui.element.textfield

import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import ru.aza1rat.marketsimapp.core.ui.element.OutlinedTextFieldLayout
import ru.aza1rat.marketsimapp.core.ui.theme.MarketSimTheme

@Composable
fun AuthOutlinedTextField(
    textFieldState: TextFieldState,
    placeholderText: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    errorText: String? = null,
    imeAction: ImeAction = ImeAction.Next,
    onKeyboardAction: KeyboardActionHandler? = null
) {
    OutlinedTextFieldLayout(
        textFieldState = textFieldState,
        placeholderText = placeholderText,
        modifier = modifier,
        enabled = enabled,
        errorText = errorText,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        lineLimits = TextFieldLineLimits.SingleLine,
        onKeyboardAction = onKeyboardAction
    )
}

@PreviewLightDark
@Composable
private fun AuthOutlinedTextFieldPreview() {
    MarketSimTheme {
        AuthOutlinedTextField(
            placeholderText = "Логин",
            textFieldState = rememberTextFieldState(),
        )
    }
}

@PreviewLightDark
@Composable
private fun AuthOutlinedTextFieldErrorPreview() {
    MarketSimTheme {
        AuthOutlinedTextField(
            placeholderText = "Логин",
            textFieldState = rememberTextFieldState(),
            errorText = "Текст ошибки"
        )
    }
}
