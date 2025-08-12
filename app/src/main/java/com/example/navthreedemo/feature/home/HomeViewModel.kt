package com.example.navthreedemo.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navthreedemo.data.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, errorMessage = null)
            }
            runCatching {
                Category.entries
            }.onSuccess { categories ->
                _uiState.update {
                    it.copy(categories = categories, isLoading = false)
                }
            }.onFailure {throwable ->
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = throwable.message)
                }
            }
        }
    }

    override fun onCleared() {
        Log.d("MyViewModel", "onCleared - HomeViewModel")
        super.onCleared()
    }
}