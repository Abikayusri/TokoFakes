package abika.sinau.tokofakes.apis.product.model.category


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    @SerialName("data")
    val `data`: List<Data?>? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("status")
    val status: Boolean? = null
) {
    @Serializable
    data class Data(
        @SerialName("description")
        val description: String? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("name")
        val name: String? = null
    )
}