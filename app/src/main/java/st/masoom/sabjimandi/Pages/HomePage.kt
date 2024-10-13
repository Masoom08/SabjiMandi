import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import st.masoom.sabjimandi.ProductImages
import st.masoom.sabjimandi.Products
import st.masoom.sabjimandi.RetailerCard
import st.masoom.sabjimandi.getRetailers
import androidx.compose.ui.Modifier
import st.masoom.sabjimandi.MarketCard
import st.masoom.sabjimandi.getMarkets

@Composable
fun HomePage(userName: String) {
    // Make the entire page scrollable using a ScrollState
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp,bottom = 56.dp)
    ) {
        item {
            Row {
                Text(
                    text = "नमस्कार  ",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "$userName !",
                    style = TextStyle(
                        color = Color(0xFF006400),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Your Personalized Path to Direct access to market",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Explore Product",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        item {
            // Call the horizontally scrollable product images
            ProductImages(Products())

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Nearest Market",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        items  (getMarkets()){market->
            MarketCard(market= market)
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
                    Text(
                    text = "Explore More",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        // Add retailer list inside the LazyColumn
        items(getRetailers()) { retailer ->
             RetailerCard(retailer = retailer)
        }
    }
}
