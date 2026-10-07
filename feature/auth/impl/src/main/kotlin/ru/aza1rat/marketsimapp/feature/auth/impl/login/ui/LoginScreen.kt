package ru.aza1rat.marketsimapp.feature.auth.impl.login.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.aza1rat.marketsimapp.feature.auth.impl.login.presentation.LoginIntent
import ru.aza1rat.marketsimapp.feature.auth.impl.login.presentation.LoginState

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    state: LoginState,
    onIntent: (LoginIntent) -> Unit
) {
    Box(modifier = modifier) {
        Text(TEST_TEXT)
    }
}

private const val TEST_TEXT = "Здесь могла быть ваша реклама"