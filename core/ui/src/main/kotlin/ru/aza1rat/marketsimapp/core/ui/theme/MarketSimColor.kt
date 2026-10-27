package ru.aza1rat.marketsimapp.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class MarketSimColor(
    val primary: Color,
    val primaryDisabled: Color,
    val onPrimary: Color,
    val error: Color,
    val secondaryContainer: Color,
    val tertiary: Color,
    val surface: Color,
    val onSurface: Color,
    val onSecondaryContainer: Color,
    val outline: Color,
    val outlineVariant: Color,
    val tertiaryContainer: Color
)

internal val LightPalette = MarketSimColor(
    primary = LimeYellow,
    primaryDisabled = Gray100,
    onPrimary = NearBlack,
    error = RedOrange,
    secondaryContainer = OffWhite,
    tertiary = Amber,
    surface = White,
    onSurface = NearBlack,
    onSecondaryContainer = Gray500,
    outline = LimeYellow,
    outlineVariant = Gray300,
    tertiaryContainer = Gray100
)

internal val DarkPalette = MarketSimColor(
    primary = LimeYellow,
    primaryDisabled = Gray700,
    onPrimary = NearBlack,
    error = RedOrange,
    secondaryContainer = Gray700,
    tertiary = Amber,
    surface = NearBlack,
    onSurface = White,
    onSecondaryContainer = Gray500,
    outline = Gray600,
    outlineVariant = Gray700,
    tertiaryContainer = Gray50
)

internal val LocalThemeColors = staticCompositionLocalOf<MarketSimColor> {
    error("Цвета темы не предоставлены")
}
