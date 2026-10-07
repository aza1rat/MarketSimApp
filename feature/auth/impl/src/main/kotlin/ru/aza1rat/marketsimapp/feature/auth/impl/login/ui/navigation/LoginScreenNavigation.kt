package ru.aza1rat.marketsimapp.feature.auth.impl.login.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.aza1rat.marketsimapp.core.navigation.defaultEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultExitTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopExitTransition
import ru.aza1rat.marketsimapp.feature.auth.impl.login.presentation.LoginViewModel
import ru.aza1rat.marketsimapp.feature.auth.impl.login.ui.LoginScreen

fun NavGraphBuilder.loginScreenNavigation(navController: NavController) {
    composable<LoginScreenRoute>(
        enterTransition = defaultEnterTransition,
        exitTransition = defaultExitTransition,
        popEnterTransition = defaultPopEnterTransition,
        popExitTransition = defaultPopExitTransition,
    ) {
        LoginScreenRouteContent(navController)
    }
}

@Composable
fun LoginScreenRouteContent(navController: NavController) {
    val viewModel: LoginViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            {}
        }
    }

    Scaffold() { paddingValues ->
        LoginScreen(
            modifier = Modifier.padding(paddingValues),
            state = state,
            onIntent = {}
        )
    }
}
