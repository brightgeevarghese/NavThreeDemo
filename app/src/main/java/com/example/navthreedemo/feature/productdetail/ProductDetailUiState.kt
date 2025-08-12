package com.example.navthreedemo.feature.productdetail

import com.example.navthreedemo.data.Product

data class ProductDetailUiState(
    val productId: String? = null,
    val product: Product? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

