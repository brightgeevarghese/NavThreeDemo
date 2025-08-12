package com.example.navthreedemo.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen(
    settingsUiState: SettingsUiState,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        if (settingsUiState.isLoading) {
            CircularProgressIndicator()
        } else if (settingsUiState.errorMessage != null) {
            Text(text = "Error: ${settingsUiState.errorMessage}")
        } else {
            for (setting in settingsUiState.settings) {
                ListItem(
                    headlineContent = { Text(text = setting.name) },
                )
                HorizontalDivider()
            }
        }
    }
}