package abisa.sinau.tokofakes.features.productlist

import abika.sinau.tokofakes.apis.product.LocalProductRepository
import abika.sinau.tokofakes.apis.product.model.productlist.ProductItem
import abika.sinau.tokofakes.libraries.component.component.AppTopBar
import abika.sinau.tokofakes.libraries.component.component.FailureScreen
import abika.sinau.tokofakes.libraries.component.component.LoadingScreen
import abika.sinau.tokofakes.libraries.component.product.ProductItemVerticalScreen
import abika.sinau.tokofakes.libraries.core.LocalAppConfig
import abika.sinau.tokofakes.libraries.core.state.Async
import abika.sinau.tokofakes.libraries.core.viewmodel.rememberViewModel
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.collectAsLazyPagingItems

@Composable
fun ProductList(
    categoryName: String,
    categoryId: Int,
    onItemClick: (ProductItem) -> Unit,
    actionBack: () -> Unit
) {
    val productRepository = LocalProductRepository.current
    val appConfig = LocalAppConfig.current
    val viewModel = rememberViewModel { ProductListViewModel(productRepository, appConfig) }
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sendIntent(ProductListIntent.GetProductList(categoryId))
        viewModel.sendIntent(ProductListIntent.SetCategoryName(categoryName))
    }

//    ProductListScreenNotPaging(state)
    ProductListScreenPaging(state, onItemClick, actionBack)
}

@Composable
fun ProductListScreenPaging(
    state: ProductListState,
    onItemClick: (ProductItem) -> Unit,
    actionBack: () -> Unit,
) {
    val pagingProduct = state.pagingData.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            AppTopBar(
                title = state.categoryName,
                actionBack = {
                    actionBack.invoke()
                }
            )
        }
    ) {
        LazyColumn {
            items(pagingProduct.itemCount) { index ->
                val item = pagingProduct[index]
                if (item != null) {
                    ProductItemVerticalScreen(
                        productItem = item,
                        onItemClick = {
                            onItemClick.invoke(it)
                        }
                    )
                }
            }
            when {
                pagingProduct.loadState.refresh is LoadState.Loading -> {
                    item {
                        LoadingScreen()
                    }
                }

                pagingProduct.loadState.append is LoadState.Loading -> {
                    item {
                        LoadingScreen()
                    }
                }

                pagingProduct.loadState.refresh is LoadState.Error -> {
                    item {
                        val throwable = (pagingProduct.loadState.refresh as LoadState.Error).error
                        FailureScreen(throwable.message.orEmpty())
                    }
                }

                pagingProduct.loadState.append is LoadState.Error -> {
                    item {
                        val throwable = (pagingProduct.loadState.append as LoadState.Error).error
                        FailureScreen(throwable.message.orEmpty())
                    }
                }
            }
        }
    }
}

// region paging


// endregion

// region not paging

@Composable
fun ProductListScreenNotPaging(state: ProductListState) {
    when (val asyncProduct = state.asyncProductList) {
        is Async.Loading -> {
            LoadingScreen()
        }

        is Async.Failure -> {
            val message = asyncProduct.throwable.message.orEmpty()
            FailureScreen(message)
        }

        is Async.Success -> {
            val data = asyncProduct.data
            ProductListScreen(
                name = state.categoryName,
                productList = data
            )
        }

        else -> {}
    }
}

@Composable
fun ProductListScreen(name: String, productList: List<ProductItem>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(6.dp)
    ) {
        item {
            Text(text = name, fontWeight = FontWeight.Bold)
            Spacer(Modifier.size(6.dp))
        }

        items(productList) {
            ProductItemVerticalScreen(
                productItem = it,
                onItemClick = { productItem ->

                }
            )
        }
    }
}

// endregion