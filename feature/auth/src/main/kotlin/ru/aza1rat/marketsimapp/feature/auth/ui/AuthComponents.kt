package ru.aza1rat.marketsimapp.feature.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.window.core.layout.WindowSizeClass
import ru.aza1rat.marketsimapp.core.ui.theme.MarketSimTheme
import ru.aza1rat.marketsimapp.feature.auth.R

private const val BACKGROUND_ZOOM_FOCAL_X = 0.571f

private const val BACKGROUND_ZOOM_FOCAL_Y = 0.532f

private const val BACKGROUND_CROP_EXTRA_ZOOM = 1.12f



private fun GraphicsLayerScope.bgAuthTransform(
    imageSize: Size
) {
    val frameWidth = size.width
    val frameHeight = size.height
    if (frameWidth == 0f || frameHeight == 0f) return
    val focal = Offset(BACKGROUND_ZOOM_FOCAL_X, BACKGROUND_ZOOM_FOCAL_Y)
    val baseCrop = maxOf(frameWidth / imageSize.width, frameHeight / imageSize.height)
    val scale = baseCrop * BACKGROUND_CROP_EXTRA_ZOOM
    val scaledWidth = imageSize.width * scale
    val scaledHeight = imageSize.height * scale
    val slackX = (scaledWidth - frameWidth).coerceAtLeast(0f)
    val slackY = (scaledHeight - frameHeight).coerceAtLeast(0f)
    val left = (focal.x * scaledWidth - frameWidth / 2f).coerceIn(0f, slackX)
    val top = (focal.y * scaledHeight - frameHeight / 2f).coerceIn(0f, slackY)
    transformOrigin = TransformOrigin(0f, 0f)
    scaleX = scale
    scaleY = scale
    translationX = -left
    translationY = -top
}


