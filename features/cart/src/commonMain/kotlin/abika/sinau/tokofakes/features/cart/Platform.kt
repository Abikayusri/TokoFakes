package abika.sinau.tokofakes.features.cart

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform