package abika.sinau.tokofakes.features.home

import abika.sinau.tokofakes.apis.product.LocalProductRepository
import abika.sinau.tokofakes.apis.product.ProductRepository
import abika.sinau.tokofakes.apis.product.model.category.CategoryItem
import abika.sinau.tokofakes.apis.product.model.productlist.ProductItem
import abika.sinau.tokofakes.features.home.screen.CategorySection
import abika.sinau.tokofakes.features.home.screen.HeaderSection
import abika.sinau.tokofakes.features.home.screen.ProducByRatingSection
import abika.sinau.tokofakes.features.home.state.HomeIntent
import abika.sinau.tokofakes.features.home.viewmodel.HomeViewModel
import abika.sinau.tokofakes.libraries.core.LocalAppConfig
import abika.sinau.tokofakes.libraries.core.viewmodel.rememberViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun Home(
    onItemClick: (ProductItem) -> Unit,
    onCategoryClick: (CategoryItem) -> Unit
) {
    val productRepository = LocalProductRepository.current
    val homeViewModel = rememberViewModel { HomeViewModel(productRepository) }

    val homeState by homeViewModel.uiState.collectAsState()

    Column {
        HeaderSection(homeState)
        CategorySection(
            homeState,
            tryAgainAction = {
                homeViewModel.sendIntent(HomeIntent.GetCategoryList)
            },
            onCategoryClick = onCategoryClick
        )
        ProducByRatingSection(
            homeState,
            onItemClick = {
                onItemClick.invoke(it)
            },
            tryAgainAction = {
                homeViewModel.sendIntent(HomeIntent.GetProductsByRating)
            }
        )
    }
}

@Composable
fun ProductListItem(productItem: ProductItem, onClickItem: (ProductItem) -> Unit) {
    Column(
        modifier = Modifier.clickable {
            onClickItem.invoke(productItem)
        }
    ) {
        Text(
            text = productItem.name
        )
    }
}