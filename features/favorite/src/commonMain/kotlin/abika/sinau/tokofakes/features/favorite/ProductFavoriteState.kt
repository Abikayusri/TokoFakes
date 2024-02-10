package abika.sinau.tokofakes.features.favorite

import abika.sinau.tokofakes.apis.product.model.productlist.ProductItem

data class ProductFavoriteState(
    val productList: List<ProductItem> = emptyList()
)