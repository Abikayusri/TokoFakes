package abika.sinau.tokofakes.features.home.viewmodel

import abika.sinau.tokofakes.apis.product.ProductRepository
import abika.sinau.tokofakes.features.home.state.HomeIntent
import abika.sinau.tokofakes.features.home.state.HomeState
import abika.sinau.tokofakes.libraries.core.state.Intent
import abika.sinau.tokofakes.libraries.core.viewmodel.ViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val productRepository: ProductRepository) :
    ViewModel<HomeState, HomeIntent>(
        HomeState()
    ) {

    init {
        updateUiState {
            copy(
                appName = productRepository.getAppName()
            )
        }
        sendIntent(
            HomeIntent.GetCategoryList,
        )
        sendIntent(
            HomeIntent.GetProductsByRating
        )
    }

    private fun getProductList() = viewModelScope.launch {
        productRepository.getProductList("dummy")
            .stateIn(this)
            .collectLatest {
                updateUiState {
                    copy(asyncProductList = it)
                }
            }
    }

    private fun getProductListByRating() = viewModelScope.launch {
        productRepository.getProductList(QUERY_RATING)
            .stateIn(this)
            .collectLatest {
                updateUiState {
                    copy(asyncProductListByRating = it)
                }
            }
    }

    private fun getCategoryList() = viewModelScope.launch {
        productRepository.getCategoryList()
            .stateIn(this)
            .collectLatest {
                updateUiState {
                    copy(asyncCategoryList = it)
                }
            }
    }

    override fun sendIntent(intent: Intent) {
        when (intent) {
            is HomeIntent.GetProductList -> {
                getProductList()
            }

            is HomeIntent.GetCategoryList -> {
                getCategoryList()
            }

            is HomeIntent.GetProductsByRating -> {
                getProductListByRating()
            }
        }
    }

    companion object {
        private const val QUERY_RATING = "?sort=rating"
    }
}