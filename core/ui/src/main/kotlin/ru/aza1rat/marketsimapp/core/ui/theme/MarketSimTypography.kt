package ru.aza1rat.marketsimapp.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.aza1rat.marketsimapp.core.ui.R

internal val urbanistFont = FontFamily(
    Font(resId = R.font.urbanist)
)
internal val largeFontWeight = FontWeight(600)
internal val largeFontBoldWeight = FontWeight(800)
internal val mediumFontWeight = FontWeight(400)
internal val mediumSemiboldWeight = FontWeight(600)

@Immutable
data class MarketSimTypography(
    val displayLarge: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = largeFontWeight,
        fontSize = 120.sp,
        lineHeight = 120.sp,
        letterSpacing = -(3.6).sp
    ),
    val displayMedium: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = largeFontWeight,
        fontSize = 90.sp,
        lineHeight = 90.sp,
        letterSpacing = -(2.7).sp
    ),
    val displaySmall: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = largeFontWeight,
        fontSize = 70.sp,
        lineHeight = 70.sp,
        letterSpacing = -(2.1).sp
    ),
    val displaySmallBold: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = largeFontBoldWeight,
        fontSize = 70.sp,
        lineHeight = 70.sp,
        letterSpacing = -(2.1).sp
    ),
    val headlineLarge: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = largeFontWeight,
        fontSize = 44.sp,
        lineHeight = 50.sp,
        letterSpacing = -(1.32).sp
    ),
    val headlineMedium: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = mediumFontWeight,
        fontSize = 40.sp,
        lineHeight = 40.sp,
        letterSpacing = -(1.2).sp
    ),
    val titleLarge: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = mediumFontWeight,
        fontSize = 30.sp,
        lineHeight = 30.sp,
        letterSpacing = -(0.9).sp
    ),
    val titleLargeSemibold: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = mediumSemiboldWeight,
        fontSize = 30.sp,
        lineHeight = 30.sp,
        letterSpacing = -(0.9).sp
    ),
    val titleMedium: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = mediumFontWeight,
        fontSize = 26.sp,
        lineHeight = 26.sp,
        letterSpacing = -(0.78).sp
    ),
    val titleSmall: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = mediumFontWeight,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = -(0.72).sp
    ),
    val bodyLargeSemibold: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = mediumSemiboldWeight,
        fontSize = 22.sp,
        lineHeight = 22.sp,
        letterSpacing = -(0.66).sp
    ),
    val bodyLarge: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = mediumFontWeight,
        fontSize = 20.sp,
        lineHeight = 20.sp,
        letterSpacing = -(0.6).sp
    ),
    val bodyMediumSemibold: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = mediumSemiboldWeight,
        fontSize = 18.sp,
        lineHeight = 18.sp,
        letterSpacing = -(0.54).sp
    ),
    val bodyMedium: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = mediumFontWeight,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = -(0.48).sp
    ),
    val bodySmallSemibold: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = mediumSemiboldWeight,
        fontSize = 14.sp,
        lineHeight = 14.sp,
        letterSpacing = -(0.42).sp
    ),
    val bodySmall: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = mediumFontWeight,
        fontSize = 14.sp,
        lineHeight = 14.sp,
        letterSpacing = -(0.42).sp
    ),
    val labelLargeProminent: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = mediumFontWeight,
        fontSize = 12.sp,
        lineHeight = 20.sp,
        letterSpacing = -(0.36).sp
    ),
    val labelLarge: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = mediumFontWeight,
        fontSize = 12.sp,
        lineHeight = 12.sp,
        letterSpacing = -(0.36).sp
    ),
    val labelMedium: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = mediumFontWeight,
        fontSize = 10.sp,
        lineHeight = 10.sp,
        letterSpacing = -(0.3).sp
    ),
    val labelSmall: TextStyle = TextStyle(
        fontFamily = urbanistFont,
        fontWeight = mediumFontWeight,
        fontSize = 8.sp,
        lineHeight = 8.sp,
        letterSpacing = -(0.24).sp
    )
)

internal val TypographyTokens = MarketSimTypography()

internal val LocalThemeTypography = staticCompositionLocalOf<MarketSimTypography> {
    error("Типография темы не предоставлены")
}
