package abika.sinau.tokofakes.apis.product.model

import abika.sinau.tokofakes.apis.product.local.ProductRealm
import abika.sinau.tokofakes.apis.product.model.category.CategoryItem
import abika.sinau.tokofakes.apis.product.model.category.CategoryResponse
import abika.sinau.tokofakes.apis.product.model.productdetail.ProductDetail
import abika.sinau.tokofakes.apis.product.model.productdetail.ProductDetailResponse
import abika.sinau.tokofakes.apis.product.model.productlist.ProductItem
import abika.sinau.tokofakes.apis.product.model.productlist.ProductListResponse

object Mapper {
    fun mapResponseToProductList(productListResponse: ProductListResponse): List<ProductItem> {
        return productListResponse.data?.map {
            mapItemResponseToItemList(it)
        }.orEmpty()
    }

    fun mapItemResponseToItemList(data: ProductListResponse.Data?): ProductItem {
        return ProductItem(
            id = data?.id ?: 0,
            name = data?.name.orEmpty(),
            price = data?.price ?: 0.0,
            rating = data?.rating ?: 0.0,
            image = data?.images.orEmpty(),
            discount = data?.discount ?: 0,
            category = CategoryItem(
                id = data?.category?.id ?: 0,
                name = data?.category?.name.orEmpty(),
                description = data?.category?.description.orEmpty(),
            )
        )
    }

    fun mapResponseToCategoryList(categoryResponse: CategoryResponse?): List<CategoryItem> {
        return categoryResponse?.data?.map {
            mapItemCategoryResponseToCategoryItem(it)
        }.orEmpty()
    }

    private fun mapItemCategoryResponseToCategoryItem(itemResponse: CategoryResponse.Data?): CategoryItem {
        return CategoryItem(
            id = itemResponse?.id ?: 0,
            name = itemResponse?.name.orEmpty(),
            description = itemResponse?.description.orEmpty()
        )
    }

    fun mapResponseToDetail(productDetailResponse: ProductDetailResponse.DataResponse): ProductDetail {
        return ProductDetail(
            id = productDetailResponse.id ?: 0,
            name = productDetailResponse.name.orEmpty(),
            description = productDetailResponse.description.orEmpty(),
            price = productDetailResponse.price ?: 0.0,
            image = productDetailResponse.images?.get(0).orEmpty()
        )
    }

    fun realmMapFromDetail(detail: ProductDetail): ProductRealm {
        return ProductRealm()
            .apply {
                id = detail.id
                name = detail.name
                price = detail.price
                description = detail.description
                image = detail.image
            }
    }

    fun realmMapToItem(realm: ProductRealm): ProductItem {
        return ProductItem(
            id = realm.id,
            name = realm.name,
            price = realm.price,
            image = realm.image,
            discount = 0,
            rating = 0.0,
            category = CategoryItem(0, "", "")
        )
    }
}