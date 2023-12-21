package abika.sinau.tokofakes.apis.product

import abika.sinau.tokofakes.libraries.core.network.NetworkDataSource
import io.ktor.client.statement.HttpResponse

class ProductDataSources : NetworkDataSource("https://marketfake.fly.dev/") {

    suspend fun getProductList(): HttpResponse {
        val endPoint = "product"
        return getHttpResponse(endPoint)
    }
}