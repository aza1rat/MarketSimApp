package ru.aza1rat.marketsimapp.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

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
    primary = Color(0xFFEFF422),
    error = Color(0xFFFF4B26),
    secondaryContainer = Color(0xFFFAFAF4),
    tertiary = Color(0xFFFFB800),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF181818),
    onSecondaryContainerVariant = Color(0xFFA0A0A0),
    outline = Color(0xFFD0D0D0),
    outlineVariant = Color(0xFFEFF422),
    tertiaryContainer = Color(0xFFF9F9F9)
)

internal val DarkPalette = MarketSimColor(
    primary = Color(0xFFEFF422),
    error = Color(0xFFFF4B26),
    secondaryContainer = Color(0xFF4D4D4D),
    tertiary = Color(0xFFFFB800),
    surface = Color(0xFF181818),
    onSurface = Color(0xFFFFFFFF),
    onSecondaryContainerVariant = Color(0xFFA0A0A0),
    outline = Color(0xFF4D4D4D),
    outlineVariant = Color(0xFF5C5C5C),
    tertiaryContainer = Color(0xFFF5F5F5)
)

