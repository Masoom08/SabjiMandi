package st.masoom.sabjimandi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class Market(
    val name: String,
    val description: String,
    val imageRes: Int, // Resource ID for the image
    val likes: Int,
    val isPopular: Boolean,
    val distance: String // Time to reach the market
)

fun getMarkets(): List<Market> {
    return listOf(
        Market(
            name = "Lucknow Mandi",
            description = "Lucknow Sabzi Mandi is a bustling, vibrant market at the heart of the city, known for its lively atmosphere and an extensive variety of fresh produce.",
            imageRes = R.drawable.market_image, // Replace with your drawable resource
            likes = 1300,
            isPopular = true,
            distance = "20 mins"
        ),
        Market(
            name = "Kanpur Mandi",
            description = "Kanpur Mandi offers a wide selection of fresh vegetables, fruits, and spices. It's a perfect place for food lovers.",
            imageRes = R.drawable.mandi, // Replace with another drawable resource
            likes = 850,
            isPopular = false,
            distance = "25 mins"
        ),
        // Add more markets as needed
    )
}

@Composable
fun MarketCard(market: Market) {
    var isFavorite by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            // Image
            Image(
                painter = painterResource(id = market.imageRes),
                contentDescription = "Market Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Market details (title, description, etc.)
            Column(modifier = Modifier.padding(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = market.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    // Favorite Icon
                    IconButton(onClick = { isFavorite = !isFavorite }) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) Color.Red else Color.Gray
                        )
                    }
                }

                Text(text = "Market", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))

                // Likes and Popular indicator
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${market.likes} Love this",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    if (market.isPopular) {
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Popular",
                            fontSize = 14.sp,
                            color = Color.Green
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(text = market.description, fontSize = 14.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(8.dp))

                // Distance
                Text(text = market.distance, fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}
