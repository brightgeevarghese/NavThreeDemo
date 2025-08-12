package com.example.navthreedemo.feature.settings

import com.example.navthreedemo.data.AppSettings

data class SettingsUiState(
    val settings: List<AppSettings> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
