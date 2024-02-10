package abika.sinau.tokofakes.features.home.screen

import abika.sinau.tokofakes.apis.product.model.category.CategoryItem
import abika.sinau.tokofakes.features.home.state.HomeState
import abika.sinau.tokofakes.libraries.component.component.FailureScreen
import abika.sinau.tokofakes.libraries.component.component.LoadingScreen
import abika.sinau.tokofakes.libraries.core.state.Async
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategorySection(
    homeState: HomeState,
    tryAgainAction: () -> Unit,
    onCategoryClick: (CategoryItem) -> Unit
) {
    val stateGrid = rememberLazyGridState()

    when (val asyncCategoryList = homeState.asyncCategoryList) {
        is Async.Loading -> {
            LoadingScreen()
        }

        is Async.Success -> {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = stateGrid,
                contentPadding = PaddingValues(6.dp)
            ) {
                val categoryList = asyncCategoryList.data
//                if (categoryList.size > 5) {
//                    items(categoryList.take(5)) { category ->
//                        Category(category)
//                    }
//                } else {
//                    items(categoryList.take(5)) { category ->
//                        Category(category)
//                    }
//                }

                items(if (categoryList.size > 5) categoryList.take(5) else categoryList) { category ->
                    Category(category, onCategoryClick)
                }

                val moreCategory = CategoryItem(
                    id = -1,
                    name = "More",
                    description = ""
                )
                item {
                    Category(moreCategory, onCategoryClick)
                }
            }
        }

        is Async.Failure -> {
            val message = asyncCategoryList.throwable.message.orEmpty()
            FailureScreen(message, tryAgainAction)
        }

        else -> {}
    }
}

@Composable
fun Category(categoryItem: CategoryItem, onCategoryClick: (CategoryItem) -> Unit) {

    val colorItem by derivedStateOf {
        if (categoryItem.id == -1) {
            Color.Blue.copy(alpha = 0.3f)
        } else {
            Color.Black.copy(alpha = 0.3f)
        }
    }

    val onItemClickModifier = remember {
        Modifier.clickable { onCategoryClick.invoke(categoryItem) }
    }

    Box(
        modifier = Modifier
            .padding(6.dp)
            .height(60.dp)
            .fillMaxWidth()
            .background(
                color = colorItem,
                shape = RoundedCornerShape(6.dp),
            )
            .then(onItemClickModifier)
            .padding(6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = categoryItem.name,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}