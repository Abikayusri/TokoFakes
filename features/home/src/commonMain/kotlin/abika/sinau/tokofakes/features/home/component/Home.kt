package abika.sinau.tokofakes.features.home.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import abika.sinau.tokofakes.apis.product.ProductRepository
import abika.sinau.tokofakes.apis.product.model.ProductList
import abika.sinau.tokofakes.features.home.viewmodel.HomeViewModel
import abika.sinau.tokofakes.libraries.core.viewmodel.rememberViewModel

@Composable
fun Home() {
    val productRepository = remember { ProductRepository() }
    val homeViewModel = rememberViewModel { HomeViewModel(productRepository) }
//    val productList = remember { productApi.getProductList() }
    val productList by homeViewModel.productList.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getProductList()
    }

    LazyColumn {
        items(productList) {
            ProductListItem(it)
        }
    }
}

@Composable
fun ProductListItem(productList: ProductList) {
    Text(
        text = productList.name
    )
}