package abika.sinau.tokofakes.features.login

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform