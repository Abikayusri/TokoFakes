package abika.sinau.tokofakes.features.home.state

import abika.sinau.tokofakes.libraries.core.state.Intent

sealed class HomeIntent : Intent {
    data object GetProductList: HomeIntent()
    data object GetCategoryList: HomeIntent()
    data object GetProductsByRating: HomeIntent()
}