import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import abika.sinau.tokofakes.features.home.component.Home
import abika.sinau.tokofakes.libraries.core.viewmodel.LocalViewModelHost
import abika.sinau.tokofakes.libraries.core.viewmodel.ViewModelHost
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    val viewModelHost = remember { ViewModelHost() }

    CompositionLocalProvider(
        LocalViewModelHost provides viewModelHost
    ) {
        MaterialTheme {
//        var showContent by remember { mutableStateOf(false) }
//        val greeting = remember { Greeting().greet() }
//        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent) {
//                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                    Image(painterResource("compose-multiplatform.xml"), null)
//                    Text("Compose: $greeting")
//                }
//            }
//        }

            Home()
        }
    }
}