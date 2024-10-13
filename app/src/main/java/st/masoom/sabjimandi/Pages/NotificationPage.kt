
import androidx.compose.foundation.layout.* // For spacing and layout
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Row

@Composable
fun NotificationPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) // Add padding around the screen
    ) {
        Row {
            Text(
                text = "Notification ",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Notification 1
        NotificationCard(
            title = "New message from Farmer's Market",
            body = "Your favorite vendor has new products in stock!"
        )

        Spacer(modifier = Modifier.height(16.dp)) // Space between notifications

        // Notification 2
        NotificationCard(
            title = "Order Confirmation",
            body = "Your order #12345 has been confirmed."
        )

        Spacer(modifier = Modifier.height(16.dp)) // Space between notifications

        // Notification 3
        NotificationCard(
            title = "Discount Alert!",
            body = "Get 20% off on all seasonal fruits this weekend."
        )
    }
}


@Composable
fun NotificationCard(title: String, body: String) {
    Card(
        backgroundColor = Color(0xFFC1FFC1), // Light background for the card
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp), // Rounded corners for a clean look
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Notification title
            Text(
                text = title,
                style = TextStyle(fontSize = 18.sp, color = Color.Black),
            )

            Spacer(modifier = Modifier.height(8.dp)) // Space between title and body

            // Notification body
            Text(
                text = body,
                style = TextStyle(fontSize = 14.sp, color = Color.Gray),
            )
        }
    }
}
