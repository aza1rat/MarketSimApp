package ru.aza1rat.marketsimapp.feature.auth.register.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.aza1rat.marketsimapp.core.navigation.defaultEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultExitTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopExitTransition

fun NavGraphBuilder.registerScreenNavigation(onNavigateToMain: () -> Unit) {
    composable<RegisterScreenRoute>(
        enterTransition = defaultEnterTransition,
        exitTransition = defaultExitTransition,
        popEnterTransition = defaultPopEnterTransition,
        popExitTransition = defaultPopExitTransition,
    ) {
        RegisterScreen(onNavigateToMain)
    }
}

@Suppress("EmptyFunctionBlock", "UnusedParameter")
@Composable
fun RegisterScreen(onNavigateToMain: () -> Unit) {
}
