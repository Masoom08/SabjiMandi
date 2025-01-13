package st.masoom.sabjimandi.Pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@Composable
fun Cart(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Empty cart message
        Text(
            text = "Your cart is empty",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        )

        // Button to explore products
        Button(
            onClick = {
                navController.navigate("scroll") // Navigate back to the home page
            },colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF006401),
                contentColor = Color.White),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Explore Products")
        }
    }
}
