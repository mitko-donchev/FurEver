package com.epicmillennium.furever.presentation.ui.navigation

import androidx.annotation.DrawableRes
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.epicmillennium.furever.R
import com.epicmillennium.furever.presentation.ui.navigation.AppDestinations.SPLASH_SCREEN

/**
 * Destinations used in the [FurEverApp].
 */
object AppDestinations {
    const val SPLASH_SCREEN = "splash_screen"
    const val PROFILE = "profile"
    const val LIKED = "liked"
    const val HOME = "home"
    const val MESSAGES = "messages"
}

data class AppTopLevelDestination(
    val route: String,
    @DrawableRes
    val selectedIcon: Int,
    @DrawableRes
    val unselectedIcon: Int,
    val iconTextId: Int,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

/**
 * Models the navigation actions in the app.
 */
class AppNavigationActions(private val navController: NavController) {
    fun navigateToHome() {
        navController.navigate(AppDestinations.HOME) {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }

    fun navigateTo(destination: AppTopLevelDestination) {
        navController.navigate(destination.route) {
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
        selectedIcon = R.drawable.ic_profile_selected,
        unselectedIcon = R.drawable.ic_profile_unselected,
        iconTextId = R.string.tab_profile,
        hasNews = true
    ),
    AppTopLevelDestination(
        route = AppDestinations.LIKED,
        selectedIcon = R.drawable.ic_liked_selected,
        unselectedIcon = R.drawable.ic_liked_unselected,
        iconTextId = R.string.tab_liked,
        hasNews = false
    ),
    AppTopLevelDestination(
        route = AppDestinations.HOME,
        selectedIcon = R.drawable.ic_home_selected,
        unselectedIcon = R.drawable.ic_home_unselected,
        iconTextId = R.string.tab_home,
        hasNews = false
    ),
    AppTopLevelDestination(
        route = AppDestinations.MESSAGES,
        selectedIcon = R.drawable.ic_chat_selected,
        unselectedIcon = R.drawable.ic_chat_unselected,
        iconTextId = R.string.tab_messages,
        hasNews = false,
        badgeCount = 3
    ),
)