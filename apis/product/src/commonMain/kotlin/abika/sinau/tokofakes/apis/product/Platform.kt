package abika.sinau.tokofakes.apis.product

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform