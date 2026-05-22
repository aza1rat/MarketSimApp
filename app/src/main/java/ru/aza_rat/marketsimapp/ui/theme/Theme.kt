package ru.aza_rat.marketsimapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDarkColor,
    onPrimary = OnPrimaryDarkColor,
    primaryContainer = PrimaryContainerDarkColor,
    background = SurfaceDarkColor,
    onBackground = OnSurfaceDarkColor,
    surface = SurfaceDarkColor,
    onSurface = OnSurfaceDarkColor,
    surfaceContainer = SurfaceContainerDarkColor,
    secondaryContainer = SecondaryContainerDarkColor,
    onSecondaryContainer = OnSecondaryContainerDarkColor,
    onSurfaceVariant = OnSurfaceVariantDarkColor,
    surfaceContainerHighest = SurfaceContainerHighestDarkColor,
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = OnPrimaryColor,
    primaryContainer = PrimaryContainerColor,
    background = SurfaceColor,
    onBackground = OnSurfaceColor,
    surface = SurfaceColor,
    onSurface = OnSurfaceColor,
    surfaceContainer = SurfaceContainerColor,
    secondaryContainer = SecondaryContainerColor,
    onSecondaryContainer = OnSecondaryContainerColor,
    onSurfaceVariant = OnSurfaceVariantColor,
    surfaceContainerHighest = SurfaceContainerHighestColor
)

@Composable
fun MarketSimAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}