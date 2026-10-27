package ru.aza1rat.marketsimapp.core.ui.element.textfield

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
    label: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorText: String? = null,
    imeAction: ImeAction = ImeAction.Next
) {
    OutlinedTextFieldLayout(
        textFieldState = textFieldState,
        label = label,
        modifier = modifier,
        isError = isError,
        errorText = errorText,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        lineLimits = TextFieldLineLimits.SingleLine
    )
}

@PreviewLightDark
@Composable
private fun AuthOutlinedTextFieldPreview() {
    MarketSimTheme {
        AuthOutlinedTextField(
            label = "Логин",
            textFieldState = rememberTextFieldState(),
            isError = false,
        )
    }
}

@PreviewLightDark
@Composable
private fun AuthOutlinedTextFieldErrorPreview() {
    MarketSimTheme {
        AuthOutlinedTextField(
            label = "Логин",
            textFieldState = rememberTextFieldState(),
            isError = true,
            errorText = "Текст ошибки"
        )
    }
}
