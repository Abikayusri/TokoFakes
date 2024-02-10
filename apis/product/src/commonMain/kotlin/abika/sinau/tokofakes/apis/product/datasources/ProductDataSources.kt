package abika.sinau.tokofakes.apis.product.datasources

import abika.sinau.tokofakes.libraries.core.AppConfig
import abika.sinau.tokofakes.libraries.core.network.NetworkDataSource
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.delay

class ProductDataSources(
    private val appConfig: AppConfig,
) : NetworkDataSource(appConfig.baseUrl) {

    suspend fun getProductList(query: String): HttpResponse {
        val endPoint = "product$query"
        delay(2000)
        return getHttpResponse(endPoint)
    }

    suspend fun getCategoryList(): HttpResponse {
        val endPoint = "product/category"
        delay(2000)
        return getHttpResponse(endPoint)
    }

    suspend fun getProductDetail(productId: Int): HttpResponse {
        val endPoint = "product/$productId"
        delay(1000)
        return getHttpResponse(endPoint)
    }
}