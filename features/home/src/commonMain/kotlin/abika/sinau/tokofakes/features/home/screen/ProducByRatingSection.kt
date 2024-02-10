package abika.sinau.tokofakes.features.home.screen

import abika.sinau.tokofakes.apis.product.model.productlist.ProductItem
import abika.sinau.tokofakes.features.home.state.HomeState
import abika.sinau.tokofakes.libraries.component.component.FailureScreen
import abika.sinau.tokofakes.libraries.component.component.LoadingScreen
import abika.sinau.tokofakes.libraries.component.product.ProductItemGridScreen
import abika.sinau.tokofakes.libraries.core.state.Async
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProducByRatingSection(
    homeState: HomeState,
    onItemClick: (ProductItem) -> Unit,
    tryAgainAction: () -> Unit
) {

    Row(
        modifier = Modifier.padding(
            horizontal = 12.dp
        ).padding(
            top = 12.dp
        )
    ) {
        Text(
            text = "Top Products",
            fontSize = 18.sp
        )
    }

    when (val async = homeState.asyncProductListByRating) {
        is Async.Loading -> {
            LoadingScreen()
        }

        is Async.Success -> {
            val products = async.data
            LazyRow(
                contentPadding = PaddingValues(6.dp)
            ) {
                items(products) { product ->
                    ProductItemGridScreen(product, onItemClick)
                }
            }
        }

        is Async.Failure -> {
            val message = async.throwable.message.orEmpty()
            FailureScreen(message, tryAgainAction)
        }

        else -> {}
    }
}