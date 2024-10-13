import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import st.masoom.sabjimandi.Product
import st.masoom.sabjimandi.ProductImages
import st.masoom.sabjimandi.Products
import st.masoom.sabjimandi.R // Update this to your actual package

@Composable
fun SearchPage() {
    var query by remember { mutableStateOf("") }

    val itemList = Products()

    // Filter items based on query
    val filteredItems = itemList.filter { it.name.contains(query, ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search bar input field
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(fontSize = 18.sp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFE0E0E0),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display the filtered items if the search query is not empty
        if (query.isNotEmpty()) {
            LazyColumn {
                items(filteredItems) { product ->
                    ProductRow(product = product)
                }
            }
        } else {
            Text(
                text = "TRENDING SEARCHES",
                style = TextStyle(fontSize = 16.sp, color = Color.Gray),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Explore Product",
            style = TextStyle(
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        ProductImages(itemList)
    }
}

@Composable
fun ProductRow(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Circular image for each product
        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = product.name,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Product name
        Text(
            text = product.name,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}