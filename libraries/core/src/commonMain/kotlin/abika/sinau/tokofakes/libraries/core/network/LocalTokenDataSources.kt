package abika.sinau.tokofakes.libraries.core.network

import abika.sinau.tokofakes.libraries.core.local.ValueDataSources
import androidx.compose.runtime.compositionLocalOf

class LocalTokenDataSources(
    private val valueDataSources: ValueDataSources
) : TokenDataSources {

    override val getToken: String
        get() {
            return valueDataSources.getString("token")
        }

    fun saveToken(token: String) {
        valueDataSources.setString("token", token)
    }
}

val LocalLocalTokenDataSources = compositionLocalOf<LocalTokenDataSources> {
    error("Local token data sources not provided")
}