package st.masoom.sabjimandi

import android.R.style
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CheckboxDefaults.colors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import st.masoom.sabjimandi.Pages.PaymentActivity

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
fun ProductImages(productList: List<Product>, navController: NavController) {
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
                    .clickable {
                        // Navigate to the product detail page, passing the product name as argument
                        navController.navigate("productDetail/${product.name}")
                    }
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

@Composable
fun ProductDetailPage(productId: String?, context: Context) {
    var quantity by remember { mutableStateOf(1) }
    val product = Products().find { it.name == productId }
    Column(modifier = Modifier.padding(16.dp,top=80.dp)  ) {
        Text(text = "$productId",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Placeholder for product image
        //Text(text = "Product Image")
        product?.let {
            Image(
                painter = painterResource(id = it.imageRes), // Replace with actual product image if available
                contentDescription = "Product Image",
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()    // Takes the full width of the column
                    .aspectRatio(1f),   // Makes the image square , // Optional if you want a circular image
                contentScale = ContentScale.Crop
            )
        }?: run {
            // Placeholder if product is not found
            Text(text = "Product Image")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row() {
            Text(
                text = "Description: ",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Product description
            Text(text = "This is the product description.")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Quantity increment/decrement
        Row {
            TextButton(onClick = { if (quantity > 1) quantity-- }) {
                Text(text = "-",)
            }
            Text(text = quantity.toString())
            TextButton(onClick = { quantity++ }) {
                Text(text = "+")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text ="₹ 49.99")
        Spacer(modifier = Modifier.height(16.dp))
        // Add to Cart button
        Button(onClick = { /* Handle Add to Cart action */ },colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF8BFC45),
            contentColor = Color.Black
        )) {
            Text(text = "Add to Cart")
        }
        Button(onClick = {
            val intent = Intent(context, PaymentActivity::class.java)
            intent.putExtra("amount", 500) // Set the price of the product
            context.startActivity(intent) // Trigger Razorpay payment
        },colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF8BFC45),
            contentColor = Color.Black
        )) {
            Text(text = "Buy Now")
        }
    }
}