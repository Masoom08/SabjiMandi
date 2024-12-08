package st.masoom.sabjimandi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.razorpay.Checkout
import st.masoom.sabjimandi.ui.theme.SabjiMandiTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import st.masoom.sabjimandi.Login.MyAppNavigation
import kotlin.getValue

class MainActivity : ComponentActivity() {
    //private lateinit var auth: FirebaseAuth
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //FirebaseApp.initializeApp(this)
            //auth = FirebaseAuth.getInstance()
        setContent {
            SabjiMandiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Checkout.preload(applicationContext)
                   MyApp(authViewModel = authViewModel)
                    //MyAppNavigation()
                }
            }
        }
    }
}



@Composable
fun MyApp(authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    val currentRoute = navController.currentBackStackEntryFlow.collectAsState(initial = null).value?.destination?.route

    // Route where both top and bottom bars should be shown
    val hideBarsForRoutes = listOf("login", "signup")

    Scaffold(
        topBar = {
            if (currentRoute !in hideBarsForRoutes) {
            CustomTopBar()
        }
                 },
        bottomBar = { if (currentRoute !in hideBarsForRoutes) {
            BottomNavigationBar(navController = navController)
        }
        }
    ) { innerPadding ->
        NavHostContainer(navController = navController, modifier = Modifier.padding(innerPadding),authViewModel =authViewModel)
    }
}

