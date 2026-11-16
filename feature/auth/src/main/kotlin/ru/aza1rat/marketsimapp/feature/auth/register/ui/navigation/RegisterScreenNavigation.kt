package ru.aza1rat.marketsimapp.feature.auth.register.ui.navigation

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalAutofillManager
import androidx.compose.ui.platform.LocalResources
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch
import ru.aza1rat.marketsimapp.core.navigation.defaultEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultExitTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopExitTransition
import ru.aza1rat.marketsimapp.core.ui.R
import ru.aza1rat.marketsimapp.feature.auth.register.presentation.RegisterEffect
import ru.aza1rat.marketsimapp.feature.auth.register.presentation.RegisterViewModel
import ru.aza1rat.marketsimapp.feature.auth.register.ui.RegisterScreen

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
        val snackBarHostState = remember { SnackbarHostState() }
        val currentResources by rememberUpdatedState(LocalResources.current)
        val autofillManager = LocalAutofillManager.current

        LaunchedEffect(viewModel) {
            viewModel.effects.collect { effect ->
                when (effect) {
                    RegisterEffect.NavigateToMain -> {
                        autofillManager?.commit()
                        onNavigateToMain()
                    }
                    RegisterEffect.NavigateToLogin -> onNavigateToLogin()
                    is RegisterEffect.ShowError -> launch {
                        snackBarHostState.showSnackbar(
                            message = effect.message?.asString(currentResources)
                                ?: currentResources.getString(R.string.error_server)
                        )
                    }
                }
            }
        }

        DisposableEffect(autofillManager) {
            onDispose {
                autofillManager?.cancel()
            }
        }

        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
        ) { paddingValues ->
            RegisterScreen(
                state = state,
                modifier = Modifier
                    .fillMaxSize()
                    .consumeWindowInsets(paddingValues)
                    .imePadding(),
                onIntent = {
                    viewModel.dispatch(it)
                }
            )
        }
    }
}
