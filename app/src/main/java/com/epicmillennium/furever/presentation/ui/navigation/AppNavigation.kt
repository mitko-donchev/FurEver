package com.epicmillennium.furever.presentation.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.epicmillennium.furever.R

/**
 * Destinations used in the [FurEverApp].
 */
object AppDestinations {
    const val SPLASH_SCREEN = "splash_screen"
    const val PROFILE = "home"
    const val LIKED = "home"
    const val HOME = "home"
    const val MESSAGES = "home"
    const val SETTINGS = "home"
}

data class AppTopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)

/**
 * Models the navigation actions in the app.
 */
class AppNavigationActions(private val navController: NavController) {
    fun navigateToHome() {
        navController.navigate(AppDestinations.HOME) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // re-selecting the same item
            launchSingleTop = true
            // Restore state when re-selecting a previously selected item
            restoreState = true
        }
    }

    fun navigateTo(destination: AppTopLevelDestination) {
        navController.navigate(AppDestinations.HOME) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // re-selecting the same item
            launchSingleTop = true
            // Restore state when re-selecting a previously selected item
            restoreState = true
        }
    }
}

val TOP_LEVEL_DESTINATIONS = listOf(
    AppTopLevelDestination(
        route = AppDestinations.PROFILE,
        selectedIcon = Icons.Default.Person,
        unselectedIcon = Icons.Default.Person,
        iconTextId = R.string.tab_profile
    ),
    AppTopLevelDestination(
        route = AppDestinations.LIKED,
        selectedIcon = Icons.Default.Favorite,
        unselectedIcon = Icons.Default.Favorite,
        iconTextId = R.string.tab_liked
    ),
    AppTopLevelDestination(
        route = AppDestinations.HOME,
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Default.Home,
        iconTextId = R.string.tab_home
    ),
    AppTopLevelDestination(
        route = AppDestinations.MESSAGES,
        selectedIcon = Icons.Outlined.ChatBubbleOutline,
        unselectedIcon = Icons.Outlined.ChatBubbleOutline,
        iconTextId = R.string.tab_messages
    ),
    AppTopLevelDestination(
        route = AppDestinations.SETTINGS,
        selectedIcon = Icons.Default.Settings,
        unselectedIcon = Icons.Default.Settings,
        iconTextId = R.string.tab_settings
    )
)