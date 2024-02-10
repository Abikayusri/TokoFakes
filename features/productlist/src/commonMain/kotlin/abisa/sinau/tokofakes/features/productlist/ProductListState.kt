package abisa.sinau.tokofakes.features.productlist

import abika.sinau.tokofakes.apis.product.model.productlist.ProductItem
import abika.sinau.tokofakes.libraries.core.state.Async
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ProductListState(
    val categoryName: String = "",
    val asyncProductList: Async<List<ProductItem>> = Async.Default,
    val pagingData: Flow<PagingData<ProductItem>> = emptyFlow()
)
