package st.masoom.sabjimandi

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun CustomTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Sabji Mandi",
                style = TextStyle(
                    color = Color.White,    // Text color on the dark green background
                    fontSize = 20.sp        // Custom font size for the title
                )
            )
        },

        backgroundColor = Color(0xFF006400), // Use the primary theme color or a custom color
        contentColor = Color.White          // Text or icon color
        // Content color (icon/text)
        // Elevation for shadow effect
    )
}