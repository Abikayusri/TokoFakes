package abika.sinau.tokofakes.apis.product.datasources

import abika.sinau.tokofakes.apis.product.local.ProductRealm
import abika.sinau.tokofakes.apis.product.model.Mapper
import abika.sinau.tokofakes.apis.product.model.productdetail.ProductDetail
import abika.sinau.tokofakes.apis.product.model.productlist.ProductItem
import abika.sinau.tokofakes.libraries.core.local.LocalDataSources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductFavoriteDataSources : LocalDataSources(ProductRealm::class) {

    suspend fun insertProduct(productDetail: ProductDetail) {
        val realm = Mapper.realmMapFromDetail(productDetail)
        insertObject(realm)
    }

    suspend fun removeProduct(productId: Int) {
        removeObject(ProductRealm::class, productId)
    }

    suspend fun getProductIsFavorite(productId: Int): Flow<Boolean> {
        return getObjectExistById(ProductRealm::class, productId)
    }

    suspend fun getAllFavorite(): Flow<List<ProductItem>> {
        return getObjects(ProductRealm::class)
            .map {
                it.map {
                    Mapper.realmMapToItem(it)
                }
            }
    }
}