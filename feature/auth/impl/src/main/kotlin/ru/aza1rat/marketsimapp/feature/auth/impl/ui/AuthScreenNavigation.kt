package ru.aza1rat.marketsimapp.feature.auth.impl.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import ru.aza1rat.marketsimapp.feature.auth.api.AuthScreenRoute
import ru.aza1rat.marketsimapp.feature.auth.impl.login.ui.navigation.LoginScreenRoute
import ru.aza1rat.marketsimapp.feature.auth.impl.login.ui.navigation.loginScreenNavigation
import ru.aza1rat.marketsimapp.feature.auth.impl.register.ui.navigation.registerScreenNavigation

fun NavGraphBuilder.authScreenNavigation(navController: NavController) {
    navigation<AuthScreenRoute>(
        startDestination = LoginScreenRoute
    ) {
        loginScreenNavigation(navController)
        registerScreenNavigation(navController)
    }
}
