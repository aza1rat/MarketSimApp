package ru.aza1rat.marketsimapp.ui.navigation

import MainScreenRoute
import androidx.navigation.NavController
import ru.aza1rat.marketsimapp.feature.auth.register.ui.navigation.RegisterScreenRoute
import ru.aza1rat.marketsimapp.feature.auth.ui.AuthScreenRoute
import ru.aza1rat.marketsimapp.feature.cart.ui.CartScreenRoute
import ru.aza1rat.marketsimapp.feature.category.ui.CategoryScreenRoute
import ru.aza1rat.marketsimapp.feature.product.ui.ProductScreenRoute
import ru.aza1rat.marketsimapp.feature.profile.ui.ProfileScreenRoute
import ru.aza1rat.marketsimapp.feature.tracking.ui.TrackingScreenRoute

fun NavController.navigateToAuthScreen() {
    navigate(route = AuthScreenRoute)
}

fun NavController.navigateToRegisterScreen() {
    navigate(route = RegisterScreenRoute)
}

fun NavController.navigateToCartScreen() {
    navigate(route = CartScreenRoute)
}

fun NavController.navigateToCategoryScreen() {
    navigate(route = CategoryScreenRoute)
}

fun NavController.navigateToMainScreen() {
    navigate(route = MainScreenRoute)
}

fun NavController.navigateToMainScreenClearingAuth() {
    navigate(route = MainScreenRoute) {
        popUpTo<AuthScreenRoute> {
            inclusive = true
        }
    }
}

fun NavController.navigateToProductScreen() {
    navigate(route = ProductScreenRoute)
}

fun NavController.navigateToProfileScreen() {
    navigate(route = ProfileScreenRoute)
}

fun NavController.navigateToTrackingScreen() {
    navigate(route = TrackingScreenRoute)
}

fun NavController.navigateBack() {
    popBackStack()
}
