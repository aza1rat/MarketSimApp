package ru.aza1rat.marketsimapp.feature.auth.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import ru.aza1rat.marketsimapp.feature.auth.login.ui.navigation.LoginScreenRoute
import ru.aza1rat.marketsimapp.feature.auth.login.ui.navigation.loginScreenNavigation
import ru.aza1rat.marketsimapp.feature.auth.register.ui.navigation.registerScreenNavigation

fun NavGraphBuilder.authScreenNavigation(
    onNavigateToMain: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    navigation<AuthScreenRoute>(
        startDestination = LoginScreenRoute
    ) {
        loginScreenNavigation(onNavigateToMain, onNavigateToRegister)
        registerScreenNavigation(onNavigateToMain, onNavigateToLogin)
    }
}
