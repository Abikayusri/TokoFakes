package abika.sinau.tokofakes.features.home.state

import abika.sinau.tokofakes.apis.product.model.ProductList
import abika.sinau.tokofakes.libraries.core.state.Async

data class HomeState(
    val name: String = "",
    val asyncProductList: Async<List<ProductList>> = Async.Default
)
