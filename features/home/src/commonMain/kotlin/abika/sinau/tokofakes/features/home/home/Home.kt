package abika.sinau.tokofakes.features.home.home

import abika.sinau.tokofakes.apis.product.ProductRepository
import abika.sinau.tokofakes.apis.product.model.ProductList
import abika.sinau.tokofakes.libraries.core.LocalAppConfig
import abika.sinau.tokofakes.libraries.core.state.Async
import abika.sinau.tokofakes.libraries.core.viewmodel.rememberViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun Home() {
    val appConfig = LocalAppConfig.current
    val productRepository = remember { ProductRepository(appConfig) }
    val homeViewModel = rememberViewModel { HomeViewModel(productRepository) }
//    val productList = remember { productApi.getProductList() }
//    val productList by homeViewModel.productList.collectAsState()

    val homeState by homeViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
//        homeViewModel.updateName("Abika")
//        homeViewModel.getProductList()
        homeViewModel.sendIntent(
            HomeIntent.SetName("Abika"),
        )
        homeViewModel.sendIntent(
            HomeIntent.GetProductList
        )
    }

    LazyColumn {
        item {
            Text(
                text = homeState.name,
                fontWeight = FontWeight.Bold
            )
        }

//        items(productList) {
//            ProductListItem(it)
//        }

        when (val productList = homeState.asyncProductList) {
            is Async.Loading -> {
                item {
                    CircularProgressIndicator()
                }
            }

            is Async.Failure -> {
                item {
                    Text(
                        text = productList.throwable.message.orEmpty(),
                        color = Color.Red
                    )
                }
            }

            is Async.Success -> {
                items(productList.data) {
                    ProductListItem(it)
                }
            }

            else -> {}
        }
    }
}

@Composable
fun ProductListItem(productList: ProductList) {
    Text(
        text = productList.name
    )
}