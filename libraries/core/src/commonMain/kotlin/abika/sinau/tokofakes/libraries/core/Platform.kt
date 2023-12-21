package abika.sinau.tokofakes.libraries.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform