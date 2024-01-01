import abika.sinau.tokofakes.BuildKonfig
import abika.sinau.tokofakes.libraries.core.AppConfig

class AppConfigProviderImpl : AppConfig {
    override val baseUrl: String
        get() = BuildKonfig.BASE_URL
}