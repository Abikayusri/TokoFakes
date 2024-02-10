package abika.sinau.tokofakes.apis.product.model.category

import kotlinx.serialization.Serializable

@Serializable
data class CategoryItem(
    val description: String,
    val id: Int,
    val name: String,
)