package abika.sinau.tokofakes.apis.product

import abika.sinau.tokofakes.apis.product.model.Mapper
import abika.sinau.tokofakes.apis.product.model.category.CategoryItem
import abika.sinau.tokofakes.apis.product.model.category.CategoryResponse
import abika.sinau.tokofakes.apis.product.model.productlist.ProductItem
import abika.sinau.tokofakes.apis.product.model.productlist.ProductListResponse
import abika.sinau.tokofakes.libraries.core.AppConfig
import abika.sinau.tokofakes.libraries.core.repository.BaseRepository
import abika.sinau.tokofakes.libraries.core.state.Async
import androidx.compose.runtime.compositionLocalOf
import kotlinx.coroutines.flow.Flow

class ProductRepository(
    private val appConfig: AppConfig,
) : BaseRepository() {
    private val dataSources by lazy {
        ProductDataSources(appConfig)
    }
    fun getAppName() = appConfig.appName

    fun getCategoryList(): Flow<Async<List<CategoryItem>>> {
        return suspend {
            dataSources.getCategoryList()
        }.reduce<CategoryResponse, List<CategoryItem>> { response ->
            val responseData = response.data
            if (responseData.isNullOrEmpty()) {
                val throwable = Throwable("Category is Empty")
                Async.Failure(throwable)
            } else {
                val data = Mapper.mapResponseToCategoryList(response)
                Async.Success(data)
            }
        }
    }

    fun getProductList(query: String): Flow<Async<List<ProductItem>>> {

        return suspend { dataSources.getProductList(query) }.reduce<ProductListResponse, List<ProductItem>> { response ->
            val responseData = response.data
            if (responseData.isNullOrEmpty()) {
                val throwable = Throwable("Product is Empty")
                Async.Failure(throwable)
            } else {
                val data = Mapper.mapResponseToProductList(response)
                Async.Success(data)
            }
        }
    }
}

val LocalProductRepository = compositionLocalOf<ProductRepository> { error("Product repository not provided!") }