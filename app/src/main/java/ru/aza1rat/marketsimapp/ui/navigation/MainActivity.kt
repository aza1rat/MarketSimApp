package ru.aza1rat.marketsimapp.ui.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.aza1rat.marketsimapp.ui.theme.MarketSimAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarketSimAppTheme {
                val navController = rememberNavController()
                MarketSimNavHost(
                    navController = navController,
                )
            }
        }
    }
}