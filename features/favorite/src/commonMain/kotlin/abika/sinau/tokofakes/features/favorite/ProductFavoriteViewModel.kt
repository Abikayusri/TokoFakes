package abika.sinau.tokofakes.features.favorite

import abika.sinau.tokofakes.apis.product.ProductRepository
import abika.sinau.tokofakes.libraries.core.state.Intent
import abika.sinau.tokofakes.libraries.core.viewmodel.ViewModel
import kotlinx.coroutines.launch

class ProductFavoriteViewModel(
    private val productRepository: ProductRepository
) :
    ViewModel<ProductFavoriteState, ProductFavoriteIntent>(ProductFavoriteState()) {
    override fun sendIntent(intent: Intent) {
        when (intent) {
            is ProductFavoriteIntent -> {
                getProductFavorite()
            }
        }
    }

    private fun getProductFavorite() {
//        return viewModelScope.launch {
//            productRepository
//        }
    }
}