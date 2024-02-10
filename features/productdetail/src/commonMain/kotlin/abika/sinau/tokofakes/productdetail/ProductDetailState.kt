package abika.sinau.tokofakes.productdetail

import abika.sinau.tokofakes.apis.product.model.productdetail.ProductDetail
import abika.sinau.tokofakes.libraries.core.state.Async

data class ProductDetailState(
    val asyncProductDetail: Async<ProductDetail> = Async.Default,
    val isFavorite: Boolean = false
)