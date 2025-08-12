package com.example.navthreedemo.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navthreedemo.feature.productlist.ProductListUiState

@Composable
fun ProductListScreen(
    productListUiState: ProductListUiState,
    modifier: Modifier = Modifier,
    onNavigateToProductDetail: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        if (productListUiState.isLoading) {
            CircularProgressIndicator()
        } else if (productListUiState.errorMessage != null) {
            Text(text = "Error: ${productListUiState.errorMessage}")
        } else {
            Text(text = "Products in category: ${productListUiState.category}")
            LazyColumn {
                items(productListUiState.products) { product ->
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onNavigateToProductDetail(product.id) }
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(text = product.name)
                            Text(text = "Price: $${product.price}")
                        }
                    }
                }
            }
        }
    }
}