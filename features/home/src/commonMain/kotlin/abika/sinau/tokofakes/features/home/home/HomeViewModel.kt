package abika.sinau.tokofakes.features.home.home

import abika.sinau.tokofakes.apis.product.ProductRepository
import abika.sinau.tokofakes.libraries.core.state.Intent
import abika.sinau.tokofakes.libraries.core.viewmodel.ViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val productRepository: ProductRepository) : ViewModel<HomeState, HomeIntent>(
    HomeState()
) {

//    val productList = MutableStateFlow<List<ProductList>>(emptyList())

//    fun getProductList() = viewModelScope.launch {
//        productRepository.getProductList().stateIn(this).collect(productList)
//    }

    fun getProductList() = viewModelScope.launch {
        productRepository.getProductList()
            .stateIn(this)
//            .collect(productList)
            .collectLatest {
                updateUiState {
                    copy(asyncProductList = it)
                }
            }
    }

    fun updateName(name: String) = viewModelScope.launch {
        updateUiState {
            copy(name = name)
        }
    }

    override fun sendIntent(intent: Intent) {
        when(intent) {
            is HomeIntent.SetName -> {
               updateName(intent.name)
            }

            is HomeIntent.GetProductList -> {
                getProductList()
            }
        }
    }
}