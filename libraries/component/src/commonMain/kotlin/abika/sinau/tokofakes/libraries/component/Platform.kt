package abika.sinau.tokofakes.libraries.component

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform