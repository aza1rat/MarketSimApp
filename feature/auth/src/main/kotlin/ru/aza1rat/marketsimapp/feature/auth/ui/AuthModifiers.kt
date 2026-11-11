package ru.aza1rat.marketsimapp.feature.auth.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp

fun Modifier.authPanelBackground(
    backgroundColor: Color,
    shape: Shape? = null
): Modifier {
    return this.then(
        Modifier.background(
            color = backgroundColor,
            shape = shape ?: RectangleShape
        )
    )
}

fun Modifier.authHorizontalPanelBackground(
    state: ScrollState,
    backgroundColor: Color,
): Modifier {
    return this.then(
        Modifier
            .fillMaxSize()
            .verticalScroll(state)
            .authPanelBackground(backgroundColor = backgroundColor)
    )
}

fun Modifier.authHorizontalPanelFillWidth(): Modifier =
    this.then(Modifier.fillMaxWidth(AUTH_PANEL_WIDTH_FRACTION_EXPANDED))

fun Modifier.authTextField(
    bottomPadding: Dp
): Modifier {
    return this.then(
        Modifier.padding(bottom = bottomPadding)
            .semantics {
                contentType = ContentType.EmailAddress
            }
    )
}

fun Modifier.authPasswordTextField(
    bottomPadding: Dp
): Modifier {
    return this.then(
        Modifier.padding(bottom = bottomPadding)
            .semantics {
                contentType = ContentType.Password
            }
    )
}

fun Modifier.authButton(
    paddingTop: Dp
): Modifier {
    return this.then(
        Modifier.fillMaxWidth()
            .padding(top = paddingTop)
    )
}
