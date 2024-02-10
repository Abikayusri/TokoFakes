import abika.sinau.tokofakes.apis.product.LocalProductRepository
import abika.sinau.tokofakes.apis.product.ProductRepository
import abika.sinau.tokofakes.apis.product.model.category.CategoryItem
import abika.sinau.tokofakes.features.home.Home
import abika.sinau.tokofakes.libraries.component.component.LocalImageResource
import abika.sinau.tokofakes.libraries.component.utils.toData
import abika.sinau.tokofakes.libraries.component.utils.toJson
import abika.sinau.tokofakes.libraries.core.AppConfig
import abika.sinau.tokofakes.libraries.core.LocalAppConfig
import abika.sinau.tokofakes.libraries.core.viewmodel.LocalViewModelHost
import abika.sinau.tokofakes.libraries.core.viewmodel.ViewModelHost
import abika.sinau.tokofakes.productdetail.ProductDetail
import abisa.sinau.tokofakes.features.productlist.ProductList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition

@Composable
fun App() {
    val viewModelHost = remember { ViewModelHost() }
    val appConfigProviderImpl: AppConfig = remember { AppConfigProviderImpl() }
    val productRepository = remember { ProductRepository(appConfigProviderImpl) }
    val imageResourceProvider = remember { ImageResourcesProviderImpl() }

    PreComposeApp {
        CompositionLocalProvider(
            LocalViewModelHost provides viewModelHost,
            LocalAppConfig provides appConfigProviderImpl,
            LocalProductRepository provides productRepository,
            LocalImageResource provides imageResourceProvider
        ) {
            val navigator = rememberNavigator()
            NavHost(
                navigator = navigator,
                navTransition = NavTransition(),
                initialRoute = "/home"
            ) {
                scene(
                    route = "/home"
                ) {
                    Home(
                        onItemClick = {
                            navigator.navigate("/detail/${it.name}")
                        },
                        onCategoryClick = {
                            val json = it.toJson()
                            navigator.navigate("/list/$json")
                        }
                    )
                }

                scene(route = "/detail/{name}") {
                    val name = it.pathMap["name"].orEmpty()
                    ProductDetail(name)
                }

                scene(route = "/list/{category}") {
                    val dataJson = it.pathMap["category"] ?: "{}"
                    val data = dataJson.toData<CategoryItem>()
                    ProductList(
                        categoryName = data.name,
                        categoryId = data.id,
                        onItemClick = {

                        },
                        actionBack = {
                            navigator.popBackStack()
                        }
                    )
                }
            }
        }
    }

//    CompositionLocalProvider(
//        LocalViewModelHost provides viewModelHost,
//        LocalAppConfig provides appConfigProviderImpl
//    ) {
//        MaterialTheme {
////        var showContent by remember { mutableStateOf(false) }
////        val greeting = remember { Greeting().greet() }
////        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
////            Button(onClick = { showContent = !showContent }) {
////                Text("Click me!")
////            }
////            AnimatedVisibility(showContent) {
////                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
////                    Image(painterResource("compose-multiplatform.xml"), null)
////                    Text("Compose: $greeting")
////                }
////            }
////        }
//
//            Home {
//
//            }
//        }
//    }
}