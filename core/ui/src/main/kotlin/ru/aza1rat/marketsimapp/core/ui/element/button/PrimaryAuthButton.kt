package ru.aza1rat.marketsimapp.core.ui.element.button

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import ru.aza1rat.marketsimapp.core.ui.theme.MarketSimTheme

@Composable
fun PrimaryAuthButton(
    onClick: () -> Unit,
    enabled: Boolean,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    text: String = ""
) {
    val buttonEnabled = if (enabled) !isLoading else false
    val disabledContainerColor = if (!enabled) {
        MarketSimTheme.colors.primaryDisabled
    } else {
        MarketSimTheme.colors.primary
    }
    val buttonTextColor = if (enabled) {
        MarketSimTheme.colors.onPrimary
    } else {
        MarketSimTheme.colors.onSecondaryContainer
    }
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = buttonEnabled,
        shape = MarketSimTheme.roundedShapes.roundedMedium,
        colors = ButtonDefaults.buttonColors(
            containerColor = MarketSimTheme.colors.primary,
            disabledContainerColor = disabledContainerColor
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(MarketSimTheme.dimens.iconButtonMediumSize),
                color = MarketSimTheme.colors.onPrimary,
                strokeWidth = MarketSimTheme.dimens.spacingTiny
            )
        } else {
            Text(
                text = text,
                color = buttonTextColor,
                style = MarketSimTheme.typography.bodySmall,
                maxLines = 1
            )
        }
    }
}

@PreviewLightDark
@Composable
fun PrimaryAuthButtonPreview() {
    MarketSimTheme {
        PrimaryAuthButton(
            onClick = {},
            enabled = true,
            isLoading = false,
            text = "ЛОГИН"
        )
    }
}

@PreviewLightDark
@Composable
fun PrimaryAuthButtonDisabledPreview() {
    MarketSimTheme {
        PrimaryAuthButton(
            onClick = {},
            enabled = false,
            isLoading = false,
            text = "ЛОГИН",
        )
    }
}

@PreviewLightDark
@Preview(name = "Interactive Mode", widthDp = 300, heightDp = 48)
@Composable
fun PrimaryAuthButtonLoadingPreview() {
    MarketSimTheme {
        PrimaryAuthButton(
            onClick = {},
            enabled = true,
            isLoading = true,
            text = "ЛОГИН",
        )
    }
}
