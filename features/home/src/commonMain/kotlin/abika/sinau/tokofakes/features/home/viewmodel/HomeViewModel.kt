package abika.sinau.tokofakes.features.home.viewmodel

import abika.sinau.tokofakes.apis.product.ProductRepository
import abika.sinau.tokofakes.apis.product.model.ProductList
import abika.sinau.tokofakes.features.home.state.HomeState
import abika.sinau.tokofakes.libraries.core.viewmodel.ViewModel
import abika.sinau.tokofakes.libraries.core.viewmodel.ViewModelPlatform
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val productRepository: ProductRepository) : ViewModel<HomeState>(HomeState()) {

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
}