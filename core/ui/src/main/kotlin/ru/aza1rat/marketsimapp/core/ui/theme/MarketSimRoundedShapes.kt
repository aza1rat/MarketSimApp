package ru.aza1rat.marketsimapp.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Immutable
data class MarketSimRoundedShapes(
    val roundedSmall: RoundedCornerShape = RoundedCornerShape(8.dp),
    val roundedSmallPlus: RoundedCornerShape = RoundedCornerShape(12.dp),
    val roundedSM: RoundedCornerShape = RoundedCornerShape(16.dp),
    val roundedCompact: RoundedCornerShape = RoundedCornerShape(20.dp),
    val roundedMedium: RoundedCornerShape = RoundedCornerShape(28.dp),
    val roundedLarge: RoundedCornerShape = RoundedCornerShape(36.dp)
)

internal val RoundedShapes = MarketSimRoundedShapes()

internal val LocalThemeRoundedShapes = staticCompositionLocalOf<MarketSimRoundedShapes> {
    error("RoundedShapes темы не предоставлены")
}
