package ru.aza1rat.marketsimapp.core.ui.element.textfield

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import ru.aza1rat.marketsimapp.core.ui.R
import ru.aza1rat.marketsimapp.core.ui.element.OutlinedTextFieldLayout
import ru.aza1rat.marketsimapp.core.ui.theme.MarketSimTheme

@Composable
fun PasswordTextField(
    textFieldState: TextFieldState,
    label: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorText: String? = null,
    imeAction: ImeAction = ImeAction.Done,
) {
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextFieldLayout(
        textFieldState = textFieldState,
        label = label,
        modifier = modifier,
        isError = isError,
        errorText = errorText,
        outputTransformation = if (passwordVisible) null else DefaultPasswordOutputTransformation,
        keyboardType = KeyboardType.Password,
        lineLimits = TextFieldLineLimits.SingleLine,
        imeAction = imeAction,
        trailingIcon = {
            IconButton(
                onClick = { passwordVisible = !passwordVisible }
            ) {
                Icon(
                    painter = if (passwordVisible) {
                        painterResource(id = R.drawable.ic_eye_16)
                    } else {
                        painterResource(
                            id = R.drawable.ic_eye_off_16
                        )
                    },
                    modifier = Modifier.size(MarketSimTheme.dimens.iconButtonSmallSize),
                    contentDescription = null,
                    tint = MarketSimTheme.colors.onSecondaryContainer
                )
            }
        }
    )
}

@PreviewLightDark
@Preview(heightDp = 48, widthDp = 300, name = "Interactive Mode")
@Composable
private fun PasswordTextFieldPreview() {
    MarketSimTheme {
        val state =
            rememberTextFieldState(initialText = "Пароль")
        PasswordTextField(
            textFieldState = state,
            label = "Пароль",
            isError = false
        )
    }
}

@PreviewLightDark
@Composable
private fun PasswordTextFieldErrorPreview() {
    MarketSimTheme {
        val state =
            rememberTextFieldState(initialText = "Пароль")
        PasswordTextField(
            textFieldState = state,
            label = "Пароль",
            isError = true,
            errorText = "Слабый пароль"
        )
    }
}

private val DefaultPasswordOutputTransformation = OutputTransformation {
    replace(0, length, "* ".repeat(length))
}
