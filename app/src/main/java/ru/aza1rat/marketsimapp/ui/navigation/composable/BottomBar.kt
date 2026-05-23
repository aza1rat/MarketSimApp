package ru.aza1rat.marketsimapp.ui.navigation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.aza1rat.marketsimapp.ui.navigation.Destination

@Composable
fun BottomBar() {
    val selectedIndex = rememberSaveable { mutableIntStateOf(1) }
    Box {
        NavigationBar {
            Destination.entries.forEachIndexed { index, destination ->
                NavigationBarItem(selected = index == selectedIndex.intValue, onClick = {
                    selectedIndex.intValue = index
                }, icon = {
                    Icon(
                        imageVector = destination.icon, contentDescription = ""
                    )
                }, label = {
                    Text(
                        text = stringResource(destination.labelStringId),
                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing
                    )
                })
            }
        }
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.onSurfaceVariant, RectangleShape
                )
        )
    }
}