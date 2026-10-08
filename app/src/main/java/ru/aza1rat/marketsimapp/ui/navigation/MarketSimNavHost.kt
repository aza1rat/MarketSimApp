package ru.aza1rat.marketsimapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.aza1rat.marketsimapp.feature.auth.api.AuthScreenRoute
import ru.aza1rat.marketsimapp.feature.auth.impl.ui.authScreenNavigation
import ru.aza1rat.marketsimapp.feature.cart.impl.ui.cartScreenNavigation
import ru.aza1rat.marketsimapp.feature.category.impl.ui.categoryScreenNavigation
import ru.aza1rat.marketsimapp.feature.main.impl.ui.mainScreenNavigation
import ru.aza1rat.marketsimapp.feature.product.impl.ui.productScreenNavigation
import ru.aza1rat.marketsimapp.feature.profile.impl.ui.profileScreenNavigation
import ru.aza1rat.marketsimapp.feature.tracking.impl.ui.trackingScreenNavigation

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
        authScreenNavigation(navController)
        cartScreenNavigation(navController)
        categoryScreenNavigation(navController)
        mainScreenNavigation(navController)
        productScreenNavigation(navController)
        profileScreenNavigation(navController)
        trackingScreenNavigation(navController)
    }
}
