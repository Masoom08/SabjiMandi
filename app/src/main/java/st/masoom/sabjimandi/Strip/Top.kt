package st.masoom.sabjimandi

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import st.masoom.sabjimandi.ui.theme.LobsterFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Sabji Mandi",
                style = TextStyle(
                    color = Color.Black,    // Dark green color
                    fontSize = 28.sp,             // Slightly larger font for emphasis
                    fontFamily = LobsterFontFamily,  // Custom font file in res/font folder
                    fontWeight = FontWeight.ExtraBold,

                ),
                modifier = Modifier.padding(vertical = 0.dp)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF8BFC45), // Custom color for the background
            titleContentColor = Color.Black
        )
    )
}


