package abika.sinau.tokofakes.features.favorite

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform