package com.example.navthreedemo.feature.productlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navthreedemo.data.Category
import com.example.navthreedemo.data.ProductRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductListViewModel(
    private var category: Category,
    private var productRepository: ProductRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<ProductListUiState> = MutableStateFlow(
        ProductListUiState(isLoading = true, category = category)
    )
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    init {
        loadProductList()
    }

    private fun loadProductList() {
        viewModelScope.launch {
            delay(2000)
            _uiState.update {
                it.copy(isLoading = true, errorMessage = null)
            }
            runCatching {
                productRepository.getProductsByCategory(category)
            }.onSuccess { products ->
                _uiState.update {
                    it.copy(products = products, isLoading = false)
                }
            }.onFailure {throwable ->
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = throwable.message)
                }
            }
        }
    }
    override fun onCleared() {
        Log.d("MyViewModel", "onCleared - ProductListViewModel")
        super.onCleared()
    }
}