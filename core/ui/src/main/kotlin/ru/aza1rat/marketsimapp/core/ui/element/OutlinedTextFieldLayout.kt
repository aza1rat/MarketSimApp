package ru.aza1rat.marketsimapp.core.ui.element

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.aza1rat.marketsimapp.core.ui.theme.MarketSimTheme

@Composable
fun OutlinedTextFieldLayout(
    textFieldState: TextFieldState,
    placeholderText: String,
    modifier: Modifier = Modifier,
    errorText: String? = null,
    outputTransformation: OutputTransformation? = null,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
    imeAction: ImeAction = ImeAction.Unspecified,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val isError = errorText != null
    Column(modifier = modifier) {
        OutlinedTextField(
            state = textFieldState,
            modifier = Modifier.fillMaxWidth().background(
                color = MarketSimTheme.colors.secondaryContainer,
                shape = MarketSimTheme.roundedShapes.roundedMedium
            ),
            textStyle = MarketSimTheme.typography.bodySmall,
            placeholder = {
                Text(
                    text = placeholderText,
                    color = MarketSimTheme.colors.onSecondaryContainer,
                    style = MarketSimTheme.typography.bodySmall
                )
            },
            trailingIcon = trailingIcon,
            isError = isError,
            outputTransformation = outputTransformation,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            lineLimits = lineLimits,
            shape = MarketSimTheme.roundedShapes.roundedMedium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MarketSimTheme.colors.secondaryContainer,
                unfocusedBorderColor = MarketSimTheme.colors.secondaryContainer,
                errorBorderColor = MarketSimTheme.colors.error,
                errorTextColor = MarketSimTheme.colors.error,
                focusedTextColor = MarketSimTheme.colors.onSurface,
                unfocusedTextColor = MarketSimTheme.colors.onSecondaryContainer,
            ),
            contentPadding = PaddingValues(
                start = MarketSimTheme.dimens.spacingMediumPlus,
                top = MarketSimTheme.dimens.spacingMedium,
                bottom = MarketSimTheme.dimens.spacingMedium,
                end = if (trailingIcon == null) MarketSimTheme.dimens.spacingMediumPlus else 0.dp,
            )
        )
        AnimatedErrorText(isError = isError, errorText = errorText)
    }
}

@Composable
private fun AnimatedErrorText(
    isError: Boolean,
    errorText: String?
) {
    AnimatedVisibility(
        visible = isError,
        modifier = Modifier.fillMaxWidth().padding(
            top = MarketSimTheme.dimens.spacingTiny,
            start = MarketSimTheme.dimens.spacingMediumPlus,
            end = MarketSimTheme.dimens.spacingMediumPlus
        ),
        enter = fadeIn() + expandIn(),
        exit = fadeOut() + shrinkOut()
    ) {
        Text(
            text = errorText ?: "",
            color = MarketSimTheme.colors.error,
            style = MarketSimTheme.typography.bodySmall,
            maxLines = 2
        )
    }
}
