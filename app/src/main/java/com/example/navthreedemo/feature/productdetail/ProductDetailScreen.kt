package com.example.navthreedemo.feature.productdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProductDetailScreen(
    productDetailUiState: ProductDetailUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        if (productDetailUiState.isLoading) {
            CircularProgressIndicator()
        } else if (productDetailUiState.errorMessage != null) {
            Text(text = "Error: ${productDetailUiState.errorMessage}")
        } else {
            Text(text = "Product: ${productDetailUiState.product?.name}")
            Text(text = "Price: ${productDetailUiState.product?.price}")
        }
    }
}