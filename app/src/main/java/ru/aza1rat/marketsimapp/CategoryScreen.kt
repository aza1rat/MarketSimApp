package ru.aza1rat.marketsimapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.aza1rat.marketsimapp.ui.common.Title
import ru.aza1rat.marketsimapp.ui.theme.Gray

@Composable
fun CategoryScreen(innerPadding: PaddingValues, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = dimensionResource(R.dimen.main_horizontal_padding),
                end = dimensionResource(R.dimen.main_horizontal_padding),
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()
            )
    ) {
        Title(textResourceId = R.string.title_search_category)
        SearchTextField()
    }
}

@Composable
private fun SearchTextField() {
    val text = remember { mutableStateOf("") }
    BasicTextField(
        value = text.value,
        onValueChange = { text.value = it },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.large_element_vertical_padding))
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerHighest,
                shape = MaterialTheme.shapes.medium
            )
    ) { innerTextField ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(dimensionResource(R.dimen.icon_size)),
                imageVector = Icons.Default.Search,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.attached_icon_padding)))
            Box(modifier = Modifier.weight(1f)) {
                if (text.value.isEmpty()) {
                    Text(
                        text = stringResource(R.string.search_category_search_placeholder),
                        style = MaterialTheme.typography.labelMedium,
                        color = Gray
                    )
                }
                innerTextField()
            }
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.attached_icon_padding)))
            if (text.value.isNotEmpty()) {
                Icon(
                    modifier = Modifier.size(dimensionResource(R.dimen.icon_size)),
                    imageVector = Icons.Default.Close,
                    contentDescription = ""
                )
            }
        }
    }
}
