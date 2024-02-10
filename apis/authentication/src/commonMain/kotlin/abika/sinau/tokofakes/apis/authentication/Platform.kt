package abika.sinau.tokofakes.apis.authentication

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform