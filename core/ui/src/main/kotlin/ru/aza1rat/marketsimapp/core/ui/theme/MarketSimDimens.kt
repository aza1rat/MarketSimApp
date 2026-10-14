package ru.aza1rat.marketsimapp.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class MarketSimDimens(
    val edgePaddingSmall: Dp = 12.dp,
    val edgePaddingSM: Dp = 16.dp,
    val edgePaddingMedium: Dp = 20.dp,
    val edgePaddingMediumPlus: Dp = 24.dp,
    val edgePaddingAverage: Dp = 30.dp,
    val edgePaddingModerate: Dp = 40.dp,
    val edgePaddingLarge: Dp = 56.dp,
    val edgePaddingExtraLarge: Dp = 72.dp,
    val spacingTiny: Dp = 4.dp,
    val spacingExtraSmall: Dp = 8.dp,
    val spacingSmall: Dp = 12.dp,
    val spacingMedium: Dp = 16.dp,
    val spacingMediumPlus: Dp = 20.dp,
    val spacingAverage: Dp = 24.dp,
    val spacingModerate: Dp = 28.dp,
    val spacingLarge: Dp = 32.dp,
    val spacingExtraLarge: Dp = 36.dp,
    val spacingSection: Dp = 40.dp,
    val spacingSectionPlus: Dp = 44.dp,
    val spacingBlock: Dp = 52.dp,
    val spacingBlockPlus: Dp = 56.dp,
    val spacingBlockLarge: Dp = 60.dp,
    val navigationBarHeight: Dp = 80.dp,
    val chipButtonHeight: Dp = 20.dp,
    val iconButtonSmallSize: Dp = 16.dp,
    val iconButtonMediumSize: Dp = 24.dp,
    val iconButtonAverageSize: Dp = 32.dp,
    val iconButtonModerateSize: Dp = 36.dp,
    val iconButtonLargeSize: Dp = 40.dp
)

internal val DimensTokens = MarketSimDimens()

internal val LocalThemeDimens = staticCompositionLocalOf<MarketSimDimens> {
    error("Dimens темы не предоставлены")
}
