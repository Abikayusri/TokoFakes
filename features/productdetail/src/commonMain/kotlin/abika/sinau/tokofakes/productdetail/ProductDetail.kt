package abika.sinau.tokofakes.productdetail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ProductDetail(name: String) {
    Text(
        text = name,
        fontWeight = FontWeight.Bold
    )
}