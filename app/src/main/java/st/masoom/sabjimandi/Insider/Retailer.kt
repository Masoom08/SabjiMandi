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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant // Adjust according to theme
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            // Image
            Image(
                painter = painterResource(id = retailer.imageRes),
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
                        style = MaterialTheme.typography.titleMedium, // Using M3 Typography
                        fontWeight = FontWeight.Bold
                    )

                    // Favorite Icon
                    IconButton(onClick = { isFavorite = !isFavorite }) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = null,
                            tint = if (isFavorite) MaterialTheme.colorScheme.primary else Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Subtitle
                Text(
                    text = "Retailer",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Like and Popular tags
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${retailer.likes} Love this",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    if (retailer.isPopular) {
                        Text(
                            text = "Popular",
                            color = Color.Green,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Description
                Text(
                    text = retailer.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Distance row
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = retailer.distance,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

