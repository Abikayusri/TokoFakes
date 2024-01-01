package abika.sinau.tokofakes.apis.product

import abika.sinau.tokofakes.apis.product.model.Mapper
import abika.sinau.tokofakes.apis.product.model.ProductList
import abika.sinau.tokofakes.apis.product.model.ProductListResponse
import abika.sinau.tokofakes.libraries.core.AppConfig
import abika.sinau.tokofakes.libraries.core.repository.BaseRepository
import abika.sinau.tokofakes.libraries.core.state.Async
import kotlinx.coroutines.flow.Flow

class ProductRepository(
    private val appConfig: AppConfig,
) : BaseRepository() {
    private val dataSources by lazy {
        ProductDataSources(appConfig)
    }

    //    suspend fun getProductList(): Flow<List<ProductList>> {
    suspend fun getProductList(): Flow<Async<List<ProductList>>> {
//        val data = dataSources.getProductList().body<ProductListResponse>().let {
//            Mapper.mapResponseToList(it)
//        }

        return suspend { dataSources.getProductList() }.reduce<ProductListResponse, List<ProductList>> { response ->
            val responseData = response.data
            if (responseData.isNullOrEmpty()) {
                val throwable = Throwable("Product is Empty")
                Async.Failure(throwable)
            } else {
                val data = Mapper.mapResponseToList(response)
                Async.Success(data)
            }
        }

//        return flow {
//            emit(data)
//        }
    }

//    fun getProductList(): Flow<List<ProductList>> {
//        return flow {
//            emit(
//                listOf(
//                    ProductList(
//                        id = 1,
//                        name = "Meja",
//                        price = 3000.0
//                    ),
//                    ProductList(
//                        id = 2,
//                        name = "Kursi",
//                        price = 4000.0
//                    ),
//                )
//            )
//        }
//    }
}