package com.example.navthreedemo.nav

import androidx.navigation3.runtime.NavKey
import com.example.navthreedemo.data.Category
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppNavKey : NavKey {
    @Serializable data object Home : AppNavKey
    @Serializable data class ProductList(val category: Category) : AppNavKey
    @Serializable data class ProductDetail(val productId: String) : AppNavKey
    @Serializable data object Settings : AppNavKey
}

//interface AppNavKey : NavKey
//
//@Serializable
//data object Home: AppNavKey
//
//@Serializable
//data class ProductList(val category: Category): AppNavKey
//
//@Serializable
//data class ProductDetail(val productId: String): AppNavKey
//
//@Serializable
//data object Settings: AppNavKey