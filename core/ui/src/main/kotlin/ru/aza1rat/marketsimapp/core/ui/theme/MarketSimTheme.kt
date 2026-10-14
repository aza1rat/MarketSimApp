package ru.aza1rat.marketsimapp.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun MarketSimTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkPalette else LightPalette

    CompositionLocalProvider(
        LocalThemeColors provides colors,
        LocalThemeDimens provides DimensTokens,
        LocalThemeRoundedShapes provides RoundedShapes,
        LocalThemeTypography provides TypographyTokens
    ) {
        content.invoke()
    }
}

object MarketSimTheme {
    val colors: MarketSimColor
        @Composable
        @ReadOnlyComposable
        get() = LocalThemeColors.current
    val typography: MarketSimTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalThemeTypography.current
    val dimens: MarketSimDimens
        @Composable
        @ReadOnlyComposable
        get() = LocalThemeDimens.current
    val roundedShapes: MarketSimRoundedShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalThemeRoundedShapes.current
}
