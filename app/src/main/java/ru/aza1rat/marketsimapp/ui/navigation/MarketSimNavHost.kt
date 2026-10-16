package ru.aza1rat.marketsimapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.aza1rat.marketsimapp.feature.auth.ui.AuthScreenRoute
import ru.aza1rat.marketsimapp.feature.auth.ui.authScreenNavigation
import ru.aza1rat.marketsimapp.feature.cart.ui.cartScreenNavigation
import ru.aza1rat.marketsimapp.feature.category.ui.categoryScreenNavigation
import ru.aza1rat.marketsimapp.feature.main.ui.mainScreenNavigation
import ru.aza1rat.marketsimapp.feature.product.ui.productScreenNavigation
import ru.aza1rat.marketsimapp.feature.profile.ui.profileScreenNavigation
import ru.aza1rat.marketsimapp.feature.tracking.ui.trackingScreenNavigation

@Composable
fun MarketSimNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = AuthScreenRoute,
        modifier = modifier,
    ) {
        authScreenNavigation(
            onNavigateToMain = { navController.navigateToMainScreenClearingAuth() },
            onNavigateToRegister = { navController.navigateToRegisterScreen() },
            onNavigateToLogin = { navController.navigateBack() }
        )
        cartScreenNavigation()
        categoryScreenNavigation()
        mainScreenNavigation()
        productScreenNavigation()
        profileScreenNavigation()
        trackingScreenNavigation()
    }
}
