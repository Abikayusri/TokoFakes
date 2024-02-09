package abika.sinau.tokofakes.productdetail

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform