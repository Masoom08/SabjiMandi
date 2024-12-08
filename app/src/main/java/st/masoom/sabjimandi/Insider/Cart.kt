package st.masoom.sabjimandi.Insider

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import st.masoom.sabjimandi.Product

// Cart composable
@Composable
fun Cart(navController: NavController) {
    // State to manage cart items (can be replaced with a ViewModel for better state management)
    val cartItems = remember {
        mutableStateListOf(
            CartItem(Product("Onion", R.drawable.onion), 2, 30),
            CartItem(Product("Tomato", R.drawable.tomato), 1, 20),
            CartItem(Product("Potato", R.drawable.potato), 3, 15)
        )
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Your Cart",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Cart items list
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            cartItems.forEachIndexed { index, item ->
                CartItemView(item, onRemove = { cartItems.removeAt(index) })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Buy Now button
        Button(
            onClick = { /* Navigate to payment page */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8BFC45),
                contentColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Buy Now", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// Data class for CartItem
data class CartItem(val product: Product, val quantity: Int, val pricePerUnit: Int)

// Composable to display a single cart item
@Composable
fun CartItemView(item: CartItem, onRemove: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Product image
        Image(
            painter = painterResource(id = item.product.imageRes),
            contentDescription = item.product.name,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Product details
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.product.name,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Qty: ${item.quantity}  |  Price: ₹${item.quantity * item.pricePerUnit}",
                style = TextStyle(fontSize = 14.sp, color = Color.Gray)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Remove button
        Button(
            onClick = onRemove,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF6B6B),
                contentColor = Color.White
            ),
            modifier = Modifier.height(35.dp)
        ) {
            Text(text = "Remove", fontSize = 12.sp)
        }
    }
}
