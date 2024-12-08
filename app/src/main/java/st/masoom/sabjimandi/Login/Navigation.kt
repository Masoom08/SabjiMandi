package st.masoom.sabjimandi.Login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import st.masoom.sabjimandi.Login
import st.masoom.sabjimandi.Pages.HomePage
import st.masoom.sabjimandi.SignUp

@Composable
fun MyAppNavigation( /*authViewModel: AuthViewModel*/){
    val navController = rememberNavController()

    /*
    NavHost(navController= navController , startDestination ="login" ,builder={
        composable("login"){
            Login( navController /*,authViewModel*/ )
        }
        composable("signup"){
            SignUp( navController /*, authViewModel*/ )
        }

    })

     */
}