package ru.aza1rat.marketsimapp.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class MarketSimColor(
    val primary: Color,
    val error: Color,
    val secondaryContainer: Color,
    val tertiary: Color,
    val surface: Color,
    val onSurface: Color,
    val onSecondaryContainerVariant: Color,
    val outline: Color,
    val outlineVariant: Color,
    val tertiaryContainer: Color
)

internal val LightPalette = MarketSimColor(
    primary = LimeYellow,
    error = RedOrange,
    secondaryContainer = OffWhite,
    tertiary = Amber,
    surface = White,
    onSurface = NearBlack,
    onSecondaryContainerVariant = Gray500,
    outline = Gray300,
    outlineVariant = LimeYellow,
    tertiaryContainer = Gray100
)

internal val DarkPalette = MarketSimColor(
    primary = LimeYellow,
    error = RedOrange,
    secondaryContainer = Gray700,
    tertiary = Amber,
    surface = NearBlack,
    onSurface = White,
    onSecondaryContainerVariant = Gray500,
    outline = Gray700,
    outlineVariant = Gray600,
    tertiaryContainer = Gray50
)

internal val LocalThemeColors = staticCompositionLocalOf<MarketSimColor> {
    error("Цвета темы не предоставлены")
}
