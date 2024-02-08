package abika.sinau.tokofakes.apis.product

import abika.sinau.tokofakes.libraries.core.AppConfig
import abika.sinau.tokofakes.libraries.core.network.NetworkDataSource
import io.ktor.client.statement.HttpResponse

class ProductDataSources(
    private val appConfig: AppConfig
) : NetworkDataSource(appConfig.baseUrl) {

    suspend fun getProductList(): HttpResponse {
        val endPoint = "product"
        return getHttpResponse(endPoint)
    }
}