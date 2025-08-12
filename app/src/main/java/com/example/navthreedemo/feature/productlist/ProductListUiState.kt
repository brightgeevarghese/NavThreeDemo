package com.example.navthreedemo.feature.productlist

import com.example.navthreedemo.data.Category
import com.example.navthreedemo.data.Product

data class ProductListUiState(
    val category: Category,
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

