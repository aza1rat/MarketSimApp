package ru.aza1rat.marketsimapp.feature.product.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.aza1rat.marketsimapp.core.navigation.defaultEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultExitTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopExitTransition

fun NavGraphBuilder.productScreenNavigation() {
    composable<ProductScreenRoute>(
        enterTransition = defaultEnterTransition,
        exitTransition = defaultExitTransition,
        popEnterTransition = defaultPopEnterTransition,
        popExitTransition = defaultPopExitTransition,
    ) {
        ProductScreenRouteContent()
    }
}

@Suppress("EmptyFunctionBlock")
@Composable
fun ProductScreenRouteContent() {
}
