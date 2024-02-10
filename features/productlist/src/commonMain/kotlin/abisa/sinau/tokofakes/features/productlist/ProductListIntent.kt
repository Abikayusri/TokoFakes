package abisa.sinau.tokofakes.features.productlist

import abika.sinau.tokofakes.libraries.core.state.Intent

sealed class ProductListIntent : Intent {
    data class GetProductList(val categoryId: Int) : ProductListIntent()
    data class SetCategoryName(val name: String) : ProductListIntent()
}
