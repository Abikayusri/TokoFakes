package abika.sinau.tokofakes.productdetail

import abika.sinau.tokofakes.apis.product.model.productdetail.ProductDetail
import abika.sinau.tokofakes.libraries.core.state.Intent

sealed class ProductDetailIntent : Intent {
    data class GetProductDetail(val id: Int) : ProductDetailIntent()
    data class ToggleFavorite(val productDetail: ProductDetail) : ProductDetailIntent()
}