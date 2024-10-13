package st.masoom.sabjimandi

import HomePage
import NotificationPage
import SearchPage
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavigationItem.Scroll,
        NavigationItem.Search,
        NavigationItem.Notification,
        NavigationItem.Profile
    )

    BottomNavigation(
        backgroundColor = Color(0xFF006400), // Use the primary theme color or a custom color
        contentColor = Color.White
    ) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavHostContainer(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = NavigationItem.Scroll.route) {
        composable(NavigationItem.Scroll.route) { HomePage("Masoom") }
        composable(NavigationItem.Search.route) { SearchPage() }
        composable(NavigationItem.Notification.route) { NotificationPage() }
        composable(NavigationItem.Profile.route) { ProfilePage() }
    }
}

sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object Scroll : NavigationItem("scroll", Icons.Filled.Home, "Home")
    object Search : NavigationItem("search", Icons.Filled.Search, "Search")
    object Notification : NavigationItem("notification", Icons.Filled.Notifications, "Notification")
    object Profile : NavigationItem("profile", Icons.Filled.Person, "Profile")
}