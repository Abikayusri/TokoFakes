package abika.sinau.tokofakes.features.home.screen

import abika.sinau.tokofakes.features.home.state.HomeState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderSection(homeState: HomeState) {
    Row(
        modifier = Modifier.padding(
            horizontal = 12.dp
        ).padding(
            top = 12.dp
        )
    ) {
        Text(
            text = homeState.appName,
            fontSize = 18.sp
        )
    }
}