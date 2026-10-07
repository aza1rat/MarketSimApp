package ru.aza1rat.marketsimapp.feature.main.impl.ui

import MainScreenRoute
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.aza1rat.marketsimapp.core.navigation.defaultEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultExitTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopExitTransition

fun NavGraphBuilder.mainScreenNavigation(navController: NavController) {
    composable<MainScreenRoute>(
        enterTransition = defaultEnterTransition,
        exitTransition = defaultExitTransition,
        popEnterTransition = defaultPopEnterTransition,
        popExitTransition = defaultPopExitTransition,
    ) {
        MainScreenRouteContent(navController)
    }
}

@Composable
fun MainScreenRouteContent(navController: NavController) {
}
