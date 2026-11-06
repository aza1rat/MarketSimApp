package ru.aza1rat.marketsimapp.feature.auth.register.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.aza1rat.marketsimapp.core.navigation.defaultEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultExitTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopExitTransition
import ru.aza1rat.marketsimapp.feature.auth.login.ui.RegisterScreen
import ru.aza1rat.marketsimapp.feature.auth.register.presentation.RegisterEffect
import ru.aza1rat.marketsimapp.feature.auth.register.presentation.RegisterViewModel

fun NavGraphBuilder.registerScreenNavigation(
    onNavigateToMain: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    composable<RegisterScreenRoute>(
        enterTransition = defaultEnterTransition,
        exitTransition = defaultExitTransition,
        popEnterTransition = defaultPopEnterTransition,
        popExitTransition = defaultPopExitTransition,
    ) {
        val viewModel: RegisterViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        LaunchedEffect(viewModel) {
            viewModel.effects.collect { effect ->
                when (effect) {
                    RegisterEffect.NavigateToMain -> onNavigateToMain()
                    RegisterEffect.NavigateToLogin -> onNavigateToLogin()
                    is RegisterEffect.ShowError -> Log.d("AuthError", "${effect.message}")
                }
            }
        }

        Scaffold { paddingValues ->
            RegisterScreen(
                modifier = Modifier.padding(paddingValues),
            )
        }
    }
}
