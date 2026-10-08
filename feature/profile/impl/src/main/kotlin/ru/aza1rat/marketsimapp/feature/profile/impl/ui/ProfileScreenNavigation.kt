package ru.aza1rat.marketsimapp.feature.profile.impl.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.aza1rat.marketsimapp.core.navigation.defaultEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultExitTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopExitTransition
import ru.aza1rat.marketsimapp.feature.profile.api.ProfileScreenRoute

fun NavGraphBuilder.profileScreenNavigation(navController: NavController) {
    composable<ProfileScreenRoute>(
        enterTransition = defaultEnterTransition,
        exitTransition = defaultExitTransition,
        popEnterTransition = defaultPopEnterTransition,
        popExitTransition = defaultPopExitTransition,
    ) {
        ProfileScreenRouteContent(navController)
    }
}

@Suppress("EmptyFunctionBlock", "UnusedParameter")
@Composable
fun ProfileScreenRouteContent(navController: NavController) {
}
