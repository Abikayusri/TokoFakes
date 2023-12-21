package abika.sinau.tokofakes.apis.product.model

object Mapper {
    fun mapResponseToList(productListResponse: ProductListResponse): List<ProductList> {
        return productListResponse.data?.map {
            mapItemResponseToItemList(it)
        }.orEmpty()
    }

    private fun mapItemResponseToItemList(data: ProductListResponse.Data?): ProductList {
        return ProductList(
            id = data?.id ?: 0,
            name = data?.name.orEmpty(),
            price = data?.price ?: 0.0,
//            image = data?.images.orEmpty(),
//            discount = data?.discount ?: 0
        )
    }
}