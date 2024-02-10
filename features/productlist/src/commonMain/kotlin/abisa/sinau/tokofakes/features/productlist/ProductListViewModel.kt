package abisa.sinau.tokofakes.features.productlist

import abika.sinau.tokofakes.apis.product.ProductRepository
import abika.sinau.tokofakes.libraries.core.AppConfig
import abika.sinau.tokofakes.libraries.core.state.Intent
import abika.sinau.tokofakes.libraries.core.viewmodel.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val productRepository: ProductRepository,
    private val appConfig: AppConfig
) : ViewModel<ProductListState, ProductListIntent>(ProductListState()) {
    override fun sendIntent(intent: Intent) {
        when (intent) {
            is ProductListIntent.GetProductList -> {
                val categoryId = intent.categoryId
                getProductList(categoryId)
            }

            is ProductListIntent.SetCategoryName -> {
                setCategoryName(intent.name)
            }
        }
    }

    private fun getProductList(categoryId: Int) = viewModelScope.launch {
        val query = if (categoryId == -1) {
            ""
        } else {
            "?categoryId=$categoryId"
        }

        // TODO: this is not using pagination
//        productRepository
//            .getProductList(query)
//            .stateIn(this)
//            .collectLatest {
//                updateUiState {
//                    copy(
//                        asyncProductList = it
//                    )
//                }
//            }

        // TODO: this is using pagination
        Pager(
            config = PagingConfig(pageSize = 20)
        ) {
            ProductListPagingSource(appConfig, query)
        }.flow
            .cachedIn(viewModelScope)
            .also {
                updateUiState {
                    copy(
                        pagingData = it
                    )
                }
            }

    }

    private fun setCategoryName(name: String) {
        updateUiState {
            copy(
                categoryName = name
            )
        }
    }
}