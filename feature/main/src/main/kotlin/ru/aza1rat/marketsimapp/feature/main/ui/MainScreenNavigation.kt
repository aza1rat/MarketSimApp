package ru.aza1rat.marketsimapp.feature.main.ui

import MainScreenRoute
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.aza1rat.marketsimapp.core.navigation.defaultEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultExitTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopExitTransition

fun NavGraphBuilder.mainScreenNavigation() {
    composable<MainScreenRoute>(
        enterTransition = defaultEnterTransition,
        exitTransition = defaultExitTransition,
        popEnterTransition = defaultPopEnterTransition,
        popExitTransition = defaultPopExitTransition,
    ) {
        MainScreenRouteContent()
    }
}

@Suppress("EmptyFunctionBlock")
@Composable
fun MainScreenRouteContent(modifier: Modifier = Modifier) {
    Text("Регистрация", modifier = modifier.fillMaxSize())
}
