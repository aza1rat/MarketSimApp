package ru.aza1rat.marketsimapp.feature.auth.login.ui.navigation

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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch
import ru.aza1rat.marketsimapp.core.navigation.defaultEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultExitTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopEnterTransition
import ru.aza1rat.marketsimapp.core.navigation.defaultPopExitTransition
import ru.aza1rat.marketsimapp.core.ui.R
import ru.aza1rat.marketsimapp.feature.auth.login.presentation.LoginEffect
import ru.aza1rat.marketsimapp.feature.auth.login.presentation.LoginViewModel
import ru.aza1rat.marketsimapp.feature.auth.login.ui.LoginScreen

fun NavGraphBuilder.loginScreenNavigation(
    onNavigateToMain: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    composable<LoginScreenRoute>(
        enterTransition = defaultEnterTransition,
        exitTransition = defaultExitTransition,
        popEnterTransition = defaultPopEnterTransition,
        popExitTransition = defaultPopExitTransition,
    ) {
        val viewModel: LoginViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val snackBarHostState = remember { SnackbarHostState() }
        val currentResources by rememberUpdatedState(LocalResources.current)
        val autofillManager = LocalAutofillManager.current
        val lifecycleOwner = LocalLifecycleOwner.current

        LaunchedEffect(viewModel) {
            viewModel.effects.collect { effect ->
                when (effect) {
                    LoginEffect.NavigateToMain -> {
                        onNavigateToMain()
                    }
                    LoginEffect.NavigateToRegister -> {
                        onNavigateToRegister()
                    }
                    is LoginEffect.ShowError -> launch {
                        snackBarHostState.showSnackbar(
                            message = effect.message?.asString(currentResources)
                                ?: currentResources.getString(R.string.error_server),
                        )
                    }
                }
            }
        }

        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_PAUSE) {
                    autofillManager?.cancel()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }

        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
        ) { paddingValues ->
            LoginScreen(
                state = state,
                modifier = Modifier
                    .fillMaxSize()
                    .consumeWindowInsets(paddingValues)
                    .imePadding(),
                onIntent = { viewModel.dispatch(it) }
            )
        }
    }
}
