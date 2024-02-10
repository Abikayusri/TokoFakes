package abika.sinau.tokofakes.apis.product.model.category

import kotlinx.serialization.Serializable

@Serializable
data class CategoryItem(
    val id: Int,
    val description: String,
    val name: String,
)