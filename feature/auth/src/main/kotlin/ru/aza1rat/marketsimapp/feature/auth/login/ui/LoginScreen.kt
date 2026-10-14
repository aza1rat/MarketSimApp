package ru.aza1rat.marketsimapp.feature.auth.login.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.aza1rat.marketsimapp.core.ui.theme.MarketSimTheme
import ru.aza1rat.marketsimapp.feature.auth.login.presentation.LoginIntent
import ru.aza1rat.marketsimapp.feature.auth.login.presentation.LoginState

@Suppress("UnusedParameter")
@Composable
fun LoginScreen(
    state: LoginState,
    modifier: Modifier = Modifier,
    onIntent: (LoginIntent) -> Unit,
) {
    Box(modifier = modifier) {
        Text(
            text = TEST_TEXT,
            modifier = Modifier.padding(
                horizontal = MarketSimTheme.dimens.edgePaddingMedium,
                vertical = MarketSimTheme.dimens.edgePaddingAverage
            ),
            color = MarketSimTheme.colors.primary,
            style = MarketSimTheme.typography.titleLargeSemibold
        )
    }
}

private const val TEST_TEXT = "Здесь могла быть ваша реклама"
