package abika.sinau.tokofakes.features.favorite

import abika.sinau.tokofakes.apis.product.LocalProductRepository
import abika.sinau.tokofakes.apis.product.model.productlist.ProductItem
import abika.sinau.tokofakes.libraries.component.component.AppTopBar
import abika.sinau.tokofakes.libraries.component.product.ProductItemVerticalScreen
import abika.sinau.tokofakes.libraries.core.viewmodel.rememberViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment

@Composable
fun Favorite(
    onItemClick: (ProductItem) -> Unit,
) {
    val repository = LocalProductRepository.current
    val viewModel = rememberViewModel { ProductFavoriteViewModel(repository) }
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sendIntent(ProductFavoriteIntent.GetFavorite)
    }

    Scaffold(
        topBar = {
            AppTopBar(title = "Favorite")
        }
    )
    {
        LazyColumn {
            items(state.productList) {
                ProductItemVerticalScreen(
                    productItem = it,
                    onItemClick = onItemClick
                )
            }

            if (state.productList.isEmpty()) {
                item {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Product not found!")
                    }
                }
            }
        }
    }
}