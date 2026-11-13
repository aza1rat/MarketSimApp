package ru.aza1rat.marketsimapp.core.ui.element

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import ru.aza1rat.marketsimapp.core.ui.R
import ru.aza1rat.marketsimapp.core.ui.theme.MarketSimTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WindowInsets(),
    title: String = "",
    navigationAction: (() -> Unit)? = null,
    onNavigationColor: Color = MarketSimTheme.colors.onSurface,
    extraActions: @Composable (RowScope.() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MarketSimTheme.typography.titleLargeSemibold,
                color = onNavigationColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            navigationIconContentColor = onNavigationColor
        ),
        navigationIcon = {
            if (navigationAction != null) {
                IconButton(
                    onClick = navigationAction,
                    colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Transparent)
                ) {
                    Icon(painter = painterResource(R.drawable.ic_arrow_back_30), contentDescription = "")
                }
            }
        },
        actions = {
            if (extraActions != null) {
                extraActions()
            }
        },
        windowInsets = windowInsets
    )
}

@PreviewLightDark
@Composable
private fun TopNavigationBarPreview() {
    MarketSimTheme {
        TopNavigationBar(
            title = "Экран"
        )
    }
}

@PreviewLightDark
@Composable
private fun TopNavigationActionOffBarPreview() {
    MarketSimTheme {
        TopNavigationBar(
            title = "Экран",
            navigationAction = {},
        )
    }
}

@PreviewLightDark
@Composable
private fun TopNavigationTextlessBarPreview() {
    MarketSimTheme {
        TopNavigationBar(
            navigationAction = {},
        )
    }
}
