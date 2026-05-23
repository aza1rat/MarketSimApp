package ru.aza1rat.marketsimapp.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Title(paddingTop: Dp = 8.dp, @StringRes textResourceId: Int) {
    Text(
        modifier = Modifier
            .padding(top = paddingTop)
            .fillMaxWidth(),
        text = stringResource(textResourceId),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleMedium
    )
}