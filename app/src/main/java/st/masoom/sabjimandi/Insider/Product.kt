package st.masoom.sabjimandi

import androidx.compose.runtime.Composable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data class to represent a product
data class Product(val name: String, @DrawableRes val imageRes: Int)

@Composable
fun Products(): List<Product> {
    return listOf(
        Product("Onion", R.drawable.onion),
        Product("Tomato", R.drawable.tomato),
        Product("Potato", R.drawable.potato),
        Product("Green Chilli", R.drawable.green_chilli),
        Product("Capsicum", R.drawable.capsicum),
        Product("Bitter Gourd", R.drawable.bitter_gourd),
        Product("Pumpkin", R.drawable.pumpkin)
    )
}

@Composable
fun ProductImages(productList: List<Product>) {
    // Create a scroll state to manage the scroll position
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .horizontalScroll(scrollState) // Enable horizontal scrolling
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Display circular images for each product
        productList.forEach { product ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, // Center align items in the column
                modifier = Modifier
                    .padding(4.dp) // Adjusted padding around each product
            ) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.name,
                    modifier = Modifier
                        .size(60.dp) // Size of the circular images
                        .clip(CircleShape), // Make it circular
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(4.dp)) // Height spacer
                Text(
                    text = product.name,
                    style = TextStyle(
                        fontSize = 14.sp, // Adjust font size as needed
                        color = Color.Black,
                    )
                )
            }
        }
    }
}