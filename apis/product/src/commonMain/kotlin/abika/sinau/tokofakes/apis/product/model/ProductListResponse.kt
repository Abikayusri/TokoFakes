package abika.sinau.tokofakes.apis.product.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductListResponse(
    @SerialName("data")
    val `data`: List<Data?>? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("status")
    val status: Boolean? = null
) {
    @Serializable
    data class Data(
        @SerialName("category")
        val category: Category? = null,
        @SerialName("discount")
        val discount: Int? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("images")
        val images: String? = null,
        @SerialName("name")
        val name: String? = null,
        @SerialName("price")
        val price: Double? = null,
        @SerialName("rating")
        val rating: Double? = null,
        @SerialName("sort_description")
        val sortDescription: String? = null
    ) {
        @Serializable
        data class Category(
            @SerialName("description")
            val description: String? = null,
            @SerialName("id")
            val id: Int? = null,
            @SerialName("name")
            val name: String? = null
        )
    }
}