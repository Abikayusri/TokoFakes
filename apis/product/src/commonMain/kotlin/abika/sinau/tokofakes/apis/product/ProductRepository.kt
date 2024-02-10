package abika.sinau.tokofakes.apis.product

import abika.sinau.tokofakes.apis.product.datasources.ProductDataSources
import abika.sinau.tokofakes.apis.product.datasources.ProductFavoriteDataSources
import abika.sinau.tokofakes.apis.product.model.Mapper
import abika.sinau.tokofakes.apis.product.model.category.CategoryItem
import abika.sinau.tokofakes.apis.product.model.category.CategoryResponse
import abika.sinau.tokofakes.apis.product.model.productdetail.ProductDetail
import abika.sinau.tokofakes.apis.product.model.productdetail.ProductDetailResponse
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

    private val favoriteDataSources by lazy { ProductFavoriteDataSources() }

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

    fun getProductDetail(productId: Int): Flow<Async<ProductDetail>> {
        return suspend { dataSources.getProductDetail(productId) }.reduce<ProductDetailResponse, ProductDetail> { response ->
            val responseData = response.data

            if (responseData == null) {
                val throwable = Throwable("Product not found!")
                Async.Failure(throwable)
            } else {
                val data = Mapper.mapResponseToDetail(responseData)
                Async.Success(data)
            }
        }
    }

    // region realm

    suspend fun getProductFavorites(): Flow<List<ProductItem>> {
        return favoriteDataSources.getAllFavorite()
    }

    suspend fun isProductFavorite(productId: Int): Flow<Boolean> {
        return favoriteDataSources.getProductIsFavorite(productId)
    }

    suspend fun insertFavorite(productDetail: ProductDetail) {
        favoriteDataSources.insertProduct(productDetail)
    }

    suspend fun deleteFavorite(productId: Int) {
        favoriteDataSources.removeProduct(productId)
    }

    // endregion
}

val LocalProductRepository =
    compositionLocalOf<ProductRepository> { error("Product repository not provided!") }