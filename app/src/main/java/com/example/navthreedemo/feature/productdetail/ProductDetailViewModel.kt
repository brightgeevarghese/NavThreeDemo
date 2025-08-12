package com.example.navthreedemo.feature.productdetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navthreedemo.data.ProductRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    productId: String,
    productRepository: ProductRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(ProductDetailUiState(isLoading = true))
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()

    init {
        getProduct(productId, productRepository)
    }

    private fun getProduct(
        productId: String,
        productRepository: ProductRepository
    ) {
        viewModelScope.launch {
            delay(2000)
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            runCatching {
                productRepository.getProductById(productId)
            }.onSuccess { product ->
                _uiState.value = _uiState.value.copy(product = product, isLoading = false)
            }.onFailure { throwable ->
                _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = throwable.message)
            }
        }
    }
    override fun onCleared() {
        Log.d("MyViewModel", "onCleared - ProductDetailViewModel")
        super.onCleared()
    }
}
