import abika.sinau.tokofakes.features.favorite.Favorite
import abika.sinau.tokofakes.features.home.Home
import abika.sinau.tokofakes.libraries.component.utils.toJson
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator

enum class Tab {
    HOME, FAVORITE, CART
}

class TabNavigator {
    var currentTab by mutableStateOf(Tab.HOME)
}

val LocalTabNavigator = compositionLocalOf<TabNavigator> { error("Tab Navigator not provided") }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowScope.BottomTabItem(tab: Tab, pagerState: PagerState) {
    val tabNavigator = LocalTabNavigator.current

    val isSelected by derivedStateOf { tabNavigator.currentTab == tab }
    val scope = rememberCoroutineScope()

    BottomNavigationItem(
        selected = isSelected,
        onClick = {
            val page = when (tab) {
                Tab.HOME -> 0
                Tab.FAVORITE -> 1
                Tab.CART -> 2
            }

            scope.launch {
                pagerState.animateScrollToPage(page)
            }
        },
        icon = {},
        label = {
            Text(text = tab.name.lowercase())
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerScreen(navigator: Navigator) {
    val pagerState = rememberPagerState { 2 }
    val tabNavigator = LocalTabNavigator.current

    when (pagerState.currentPage) {
        0 -> {
            tabNavigator.currentTab = Tab.HOME
        }

        1 -> {
            tabNavigator.currentTab = Tab.FAVORITE
        }

        2 -> {
            tabNavigator.currentTab = Tab.CART
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomTabItem(Tab.HOME, pagerState)
                BottomTabItem(Tab.FAVORITE, pagerState)
                BottomTabItem(Tab.CART, pagerState)
            }
        }
    ) {
        HorizontalPager(
            state = pagerState,
            beyondBoundsPageCount = 3
        ) { index ->
            when (index) {
                0 -> {
                    Home(
                        onItemClick = {
                            navigator.navigate("/detail/${it.id}")
                        },
                        onCategoryClick = {
                            val json = it.toJson()
                            navigator.navigate("/list/$json")
                        }
                    )
                }

                1 -> {
                    Favorite(
                        onItemClick = {
                            navigator.navigate("/detail/${it.id}")
                        }
                    )
                }
//                2 -> {
//                    Cart {
//                        navigator.navigate(
//                            route = "/login",
//                            options = NavOptions(
//                                launchSingleTop = true,
//                                popUpTo = PopUpTo.First(true)
//                            )
//                        )
//                    }
//                }
            }
        }
    }
}