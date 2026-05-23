package ru.aza1rat.marketsimapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import ru.aza1rat.marketsimapp.R

enum class Destination(val labelStringId: Int, val icon: ImageVector) {
    Shop(R.string.destination_store, Icons.Default.Home),
    Search(R.string.destination_search, Icons.Default.Search),
    Cart(R.string.destination_cart, Icons.Default.ShoppingCart),
    Favorites(R.string.destination_favorites, Icons.Default.FavoriteBorder),
    Account(R.string.destination_account, Icons.Default.AccountCircle)
}