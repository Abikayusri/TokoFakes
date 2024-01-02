package abika.sinau.tokofakes.features.home.home

import abika.sinau.tokofakes.libraries.core.state.Intent

sealed class HomeIntent : Intent {
    data class SetName(val name: String) : HomeIntent()
    data object GetProductList: HomeIntent()
}