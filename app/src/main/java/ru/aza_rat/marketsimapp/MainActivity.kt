package ru.aza_rat.marketsimapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.aza_rat.marketsimapp.ui.navigation.composable.BottomBar
import ru.aza_rat.marketsimapp.ui.theme.MarketSimAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarketSimAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(), bottomBar = {
                        BottomBar()
                    }) { innerPadding ->
                    CategoryScreen(innerPadding)
                }
            }
        }
    }

    @Preview
    @Composable
    private fun CategoryScreenPreview() {
        MarketSimAppTheme {
            Scaffold(
                modifier = Modifier.fillMaxSize(), bottomBar = {
                    BottomBar()
                }) { innerPadding ->
                CategoryScreen(innerPadding)
            }
        }
    }

    @Preview
    @Composable
    private fun CategoryScreenDarkPreview() {
        MarketSimAppTheme(darkTheme = true) {
            Scaffold(
                modifier = Modifier.fillMaxSize(), bottomBar = {
                    BottomBar()
                }) { innerPadding ->
                CategoryScreen(innerPadding)
            }
        }
    }
}