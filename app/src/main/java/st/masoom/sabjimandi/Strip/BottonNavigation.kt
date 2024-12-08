package st.masoom.sabjimandi

import NotificationPage
import SearchPage
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.activity
import st.masoom.sabjimandi.Pages.HomePage

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavigationItem.Scroll,
        NavigationItem.Search,
        NavigationItem.Notification,
        NavigationItem.Profile
    )

    BottomNavigation(
        backgroundColor = Color(0xFF006401), // Use the primary theme color or a custom color
        contentColor = Color(0xFFFFFFFF)
    ) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = if (currentRoute == item.route) Color.White else Color.Black // White for selected, gray for unselected
                    ) },
                label = { Text(item.title,
                    color = if (currentRoute == item.route) Color.White else Color.Black // White for selected, gray for unselected
                ) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selectedContentColor = Color.White, // White color for selected
                unselectedContentColor = Color.Gray, // Gray color for unselected
                alwaysShowLabel = true
            )
        }
    }
}

@Composable
fun NavHostContainer(
    navController: NavHostController,
    modifier: Modifier
    , authViewModel: AuthViewModel) {

    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Scroll.route) {
        composable("login"){ Login( navController, authViewModel ) }
        composable("signup"){ SignUp( navController, authViewModel ) }
        composable(NavigationItem.Scroll.route) { HomePage(userName = "Rahul ", chatViewModel = ChatViewModel(), context = context) }
        composable(NavigationItem.Search.route) { SearchPage(navController = navController) }
        composable(NavigationItem.Notification.route) { NotificationPage() }
        composable(NavigationItem.Profile.route) { ProfilePage() }

    }
}

sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object Scroll : NavigationItem("scroll", Icons.Filled.Home, "Home")
    object Search : NavigationItem("search", Icons.Filled.Search, "Search")
    object Notification : NavigationItem("notification", Icons.Filled.Notifications, "Notify")
    object Profile : NavigationItem("profile", Icons.Filled.Person, "Profile")
}