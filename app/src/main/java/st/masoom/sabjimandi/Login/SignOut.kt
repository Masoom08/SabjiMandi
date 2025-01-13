package st.masoom.sabjimandi.Login

import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import androidx.navigation.NavController

fun signOut(navController: NavController) {
    // Get the FirebaseAuth instance
    val auth = FirebaseAuth.getInstance()

    // Sign out the user
    auth.signOut()

    // Optionally, show a Toast to inform the user
    Toast.makeText(navController.context, "Signed out successfully", Toast.LENGTH_SHORT).show()

    // Redirect the user to the login page after sign-out
    navController.navigate("login") {
        // Clear back stack to ensure user can't navigate back to the previous screen
        popUpTo(navController.graph.startDestinationId) { inclusive = true }
        launchSingleTop = true // Avoid adding the login screen to the back stack
        restoreState = false // Don't restore the previous state
    }
}
