package st.masoom.sabjimandi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import st.masoom.sabjimandi.ui.theme.SabjiMandiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SabjiMandiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SabjiMandiTheme {
        MyApp()
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { CustomTopBar() },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        NavHostContainer(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}