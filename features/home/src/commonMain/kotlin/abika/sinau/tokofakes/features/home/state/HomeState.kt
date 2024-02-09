package abika.sinau.tokofakes.features.home.state

import abika.sinau.tokofakes.apis.product.model.category.CategoryItem
import abika.sinau.tokofakes.apis.product.model.productlist.ProductItem
import abika.sinau.tokofakes.libraries.core.state.Async

data class HomeState(
    val appName: String = "",
    val asyncProductList: Async<List<ProductItem>> = Async.Default,
    val asyncProductListByRating: Async<List<ProductItem>> = Async.Default,
    val asyncCategoryList: Async<List<CategoryItem>> = Async.Default,
)
