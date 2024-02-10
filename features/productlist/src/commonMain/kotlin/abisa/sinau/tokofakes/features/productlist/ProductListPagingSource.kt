package abisa.sinau.tokofakes.features.productlist

import abika.sinau.tokofakes.apis.product.ProductDataSources
import abika.sinau.tokofakes.apis.product.model.Mapper
import abika.sinau.tokofakes.apis.product.model.productlist.ProductItem
import abika.sinau.tokofakes.apis.product.model.productlist.ProductListResponse
import abika.sinau.tokofakes.libraries.core.AppConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess

class ProductListPagingSource(
    appConfig: AppConfig,
    private val query: String
) : PagingSource<Int, ProductItem>() {

    private val dataSources by lazy { ProductDataSources(appConfig) }
    override fun getRefreshKey(state: PagingState<Int, ProductItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductItem> {
        val page = params.key ?: 1
        val queryPage = if (query.isNotEmpty()) {
            "$query&page=$page"
        } else {
            "?page=$page"
        }

        return try {
            val dataResponse = dataSources.getProductList(queryPage)
            val data =
                dataResponse.body<ProductListResponse>().data?.filterNotNull().orEmpty().map {
                    Mapper.mapItemResponseToItemList(it)
                }

            when {
                dataResponse.status.isSuccess() -> {
                    val nextKey = if (data.isNotEmpty()) page + 1 else null
                    val prevKey = (page - 1).takeIf { it >= 1 }
                    LoadResult.Page(
                        data = data,
                        nextKey = nextKey,
                        prevKey = prevKey
                    )
                }

                else -> {
                    val throwable = Throwable(dataResponse.bodyAsText())
                    LoadResult.Error(throwable)
                }
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}