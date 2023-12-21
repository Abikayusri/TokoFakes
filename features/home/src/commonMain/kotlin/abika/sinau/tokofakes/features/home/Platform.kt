package abika.sinau.tokofakes.features.home

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform