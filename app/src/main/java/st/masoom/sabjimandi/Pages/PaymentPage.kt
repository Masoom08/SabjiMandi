package st.masoom.sabjimandi.Pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle

@Composable
fun Payment(onBack: () -> Unit = {}) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Payment Screen", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text(text = "Go Back")
        }
    }
}
