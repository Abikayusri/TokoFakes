package abika.sinau.tokofakes.features.home.viewmodel

import abika.sinau.tokofakes.apis.product.ProductRepository
import abika.sinau.tokofakes.apis.product.model.ProductList
import abika.sinau.tokofakes.libraries.core.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val productRepository: ProductRepository) : ViewModel() {

    val productList = MutableStateFlow<List<ProductList>>(emptyList())

    fun getProductList() = viewModelScope.launch {
        productRepository.getProductList().stateIn(this).collect(productList)
    }
}