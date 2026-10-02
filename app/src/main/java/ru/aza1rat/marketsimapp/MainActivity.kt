package ru.aza1rat.marketsimapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import ru.aza1rat.marketsimapp.ui.navigation.composable.BottomBar
import ru.aza1rat.marketsimapp.ui.theme.MarketSimAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarketSimAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomBar()
                    }
                ) { innerPadding ->
                    CategoryScreen(
                        modifier = Modifier.innerVerticalPadding(innerPadding)
                    )
                }
            }
        }
    }

    @Preview
    @Composable
    private fun CategoryScreenPreview() {
        MarketSimAppTheme {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    BottomBar()
                }
            ) { innerPadding ->
                CategoryScreen(
                    modifier = Modifier.innerVerticalPadding(innerPadding)
                )
            }
        }
    }

    @Preview
    @Composable
    private fun CategoryScreenDarkPreview() {
        MarketSimAppTheme(darkTheme = true) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    BottomBar()
                }
            ) { innerPadding ->
                CategoryScreen(
                    modifier = Modifier.innerVerticalPadding(innerPadding)
                )
            }
        }
    }

    private fun Modifier.innerVerticalPadding(innerPadding: PaddingValues): Modifier = this.then(
        Modifier.padding(
            top = innerPadding.calculateTopPadding(),
            bottom = innerPadding.calculateBottomPadding()
        )
    )
}
