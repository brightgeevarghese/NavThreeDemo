package com.example.navthreedemo.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.navthreedemo.data.Category

@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    modifier: Modifier = Modifier,
    onNavigateToProductList: (Category) -> Unit,
    onNavigateToSettings: () -> Unit,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(homeUiState.categories) { category ->
            ListItem(
                headlineContent = { Text(text = category.name) },
                modifier = Modifier.clickable{onNavigateToProductList(category)}
            )
            HorizontalDivider()
        }

        item {
            ListItem(
                headlineContent = { Text(text = "Settings") },
                modifier = Modifier.clickable(onClick = onNavigateToSettings),
                trailingContent = {Text(text = "⚙\uFE0F")}
            )
        }
    }
}
