package ru.aza1rat.marketsimapp.feature.auth.impl.register.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.aza1rat.marketsimapp.core.navigation.defaultEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultExitTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopExitTransition

fun NavGraphBuilder.registerScreenNavigation(navController: NavController) {
    composable<RegisterScreenRoute>(
        enterTransition = defaultEnterTransition,
        exitTransition = defaultExitTransition,
        popEnterTransition = defaultPopEnterTransition,
        popExitTransition = defaultPopExitTransition,
    ) {
        RegisterScreenRouteContent(navController)
    }
}

@Composable
fun RegisterScreenRouteContent(navController: NavController) {
}
