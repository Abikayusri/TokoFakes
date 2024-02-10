package abisa.sinau.tokofakes.features.productlist

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform