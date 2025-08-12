package com.example.navthreedemo.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.navthreedemo.data.ProductRepository
import com.example.navthreedemo.feature.ProductListScreen
import com.example.navthreedemo.feature.home.HomeScreen
import com.example.navthreedemo.feature.home.HomeUiState
import com.example.navthreedemo.feature.home.HomeViewModel
import com.example.navthreedemo.feature.productdetail.ProductDetailScreen
import com.example.navthreedemo.feature.productdetail.ProductDetailUiState
import com.example.navthreedemo.feature.productdetail.ProductDetailViewModel
import com.example.navthreedemo.feature.productlist.ProductListUiState
import com.example.navthreedemo.feature.productlist.ProductListViewModel
import com.example.navthreedemo.feature.settings.SettingsViewModel
import com.example.navthreedemo.feature.settings.SettingsScreen
import com.example.navthreedemo.feature.settings.SettingsUiState

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack<AppNavKey>(AppNavKey.Home)
    val repository = remember { ProductRepository() }
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            //restores the back stack and serialized keys/args
            // after config change or process death.
            rememberSavedStateNavEntryDecorator(),
            //correct lifecycle scoping of ViewModels per entry
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry< AppNavKey.Home> {
                val homeViewModel: HomeViewModel = viewModel()
                val uiState: HomeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()
                HomeScreen(
                    modifier = modifier,
                    onNavigateToProductList = { category ->
                        backStack.add(AppNavKey.ProductList(category))
                    },
                    onNavigateToSettings = {
                        backStack.add(AppNavKey.Settings)
                    },
                    homeUiState = uiState
                )
            }
            entry<AppNavKey.ProductList> { productList ->
                val productListViewModel: ProductListViewModel = viewModel(
                    factory = viewModelFactory {
                        addInitializer(ProductListViewModel::class) {
                            ProductListViewModel(productList.category, repository)
                        }
                    }
                )
                val uiState: ProductListUiState by productListViewModel.uiState.collectAsStateWithLifecycle()

                ProductListScreen(
                    modifier = modifier,
                    onNavigateToProductDetail = { productId ->
                        backStack.add(AppNavKey.ProductDetail(productId))
                    },
                    productListUiState = uiState
                )
            }
            entry<AppNavKey.ProductDetail> { productDetail ->
                val productDetailViewModel: ProductDetailViewModel = viewModel(
                    factory = viewModelFactory {
                        addInitializer(ProductDetailViewModel::class) {
                            ProductDetailViewModel(productDetail.productId, repository)
                        }
                    }
                )
                val uiState: ProductDetailUiState by productDetailViewModel.uiState.collectAsStateWithLifecycle()
                ProductDetailScreen(
                    modifier = modifier,
                    productDetailUiState = uiState
                )
            }
            entry<AppNavKey.Settings> {
                val settingsViewModel: SettingsViewModel = viewModel()
                val uiState: SettingsUiState by settingsViewModel.uiState.collectAsStateWithLifecycle()
                SettingsScreen(
                    modifier = modifier,
                    settingsUiState = uiState
                )
            }
        }
    )
}