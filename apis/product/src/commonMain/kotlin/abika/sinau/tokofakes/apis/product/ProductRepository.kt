package abika.sinau.tokofakes.apis.product

import abika.sinau.tokofakes.apis.product.model.Mapper
import abika.sinau.tokofakes.apis.product.model.ProductList
import abika.sinau.tokofakes.apis.product.model.ProductListResponse
import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository {
    private val dataSources by lazy {
        ProductDataSources()
    }

    suspend fun getProductList(): Flow<List<ProductList>> {
        val data = dataSources.getProductList().body<ProductListResponse>().let {
            Mapper.mapResponseToList(it)
        }

        return flow {
            emit(data)
        }
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