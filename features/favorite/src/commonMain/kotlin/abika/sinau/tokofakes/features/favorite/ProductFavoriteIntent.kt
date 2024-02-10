package abika.sinau.tokofakes.features.favorite

import abika.sinau.tokofakes.libraries.core.state.Intent

sealed class ProductFavoriteIntent : Intent {
    data object GetFavorite : ProductFavoriteIntent()
}