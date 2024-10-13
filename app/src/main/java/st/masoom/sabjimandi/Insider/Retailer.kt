package st.masoom.sabjimandi

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Retailer(
    val name: String,
    val description: String,
    val imageRes: Int, // Drawable resource ID for the image
    val likes: Int,
    val isPopular: Boolean,
    val distance: String // Time to reach the retailer
)

fun getRetailers(): List<Retailer> {
    return listOf(
        Retailer(
            name = "Khyati Retailers",
            description = "A Lucknow Sabzi Mandi retailer offers a vibrant selection of fresh, high-quality fruits, vegetables, and spices.",
            imageRes = R.drawable.retailers_images, // Replace with your drawable resource
            likes = 1300,
            isPopular = true,
            distance = "20 mins"
        ),
        Retailer(
            name = "XYZ Retailers",
            description = "Sourcing directly from local farmers, they ensure top-notch produce.",
            imageRes = R.drawable.retailer_image1, // Replace with your drawable resource
            likes = 950,
            isPopular = false,
            distance = "30 mins"
        )
        // Add more retailers as needed
    )
}

@Composable
fun RetailerCard(retailer: Retailer) {
    var isFavorite by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            // Image
            Image(
                painter = painterResource(id = retailer.imageRes), // Use the image from the retailer object
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                // Retailer name and favorite icon
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = retailer.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    // Favorite Icon
                    IconButton(onClick = { isFavorite = !isFavorite }) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = null,
                            tint = if (isFavorite) Color.Red else Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Subtitle
                Text(
                    text = "Retailer",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Like and Popular tags
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${retailer.likes} Love this", // Show likes dynamically
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Popular tag if retailer is popular
                    if (retailer.isPopular) {
                        Text(
                            text = "Popular",
                            color = Color.Green,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .background(Color(0xFFD0FFD0), shape = RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Description
                Text(
                    text = retailer.description,
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Distance row
                Row(verticalAlignment = Alignment.CenterVertically) {
                    /*
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = null,
                        tint = Color.Green,
                        modifier = Modifier.size(14.dp)
                    )

                     */

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = retailer.distance,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

