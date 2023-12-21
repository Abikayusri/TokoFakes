package abika.sinau.tokofakes.apis.product.model

//{
//    "id": 36,
//    "name": "Vintage Wall Art",
//    "sort_description": "Vintage wall art for unique decor. Add a touch of creativity to your living space.",
//    "category": {
//    "id": 2,
//    "name": "Art & Decor",
//    "description": "Artwork"
//},
//    "price": 600000.0,
//    "rating": 4.0,
//    "discount": 0,
//    "images": "https://raw.githubusercontent.com/utsmannn/utsmannn/master/images/Vintage%20Wall%20Art/img-0.webp"
//}


data class ProductList(
    val id: Int,
    val name: String,
    val price: Double,
//    val image: String,
//    val discount: Int
)