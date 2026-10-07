package ru.aza1rat.marketsimapp.feature.product.impl.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.aza1rat.marketsimapp.core.navigation.defaultEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultExitTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopExitTransition
import ru.aza1rat.marketsimapp.feature.product.api.ProductScreenRoute

fun NavGraphBuilder.productScreenNavigation(navController: NavController) {
    composable<ProductScreenRoute>(
        enterTransition = defaultEnterTransition,
        exitTransition = defaultExitTransition,
        popEnterTransition = defaultPopEnterTransition,
        popExitTransition = defaultPopExitTransition,
    ) {
        ProductScreenRouteContent(navController)
    }
}

@Composable
fun ProductScreenRouteContent(navController: NavController) {
}
