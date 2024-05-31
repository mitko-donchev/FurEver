package com.epicmillennium.furever.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.epicmillennium.furever.presentation.theme.FurEverHomeTheme
import com.epicmillennium.furever.presentation.ui.navigation.AppDestinations.SPLASH_SCREEN
import com.epicmillennium.furever.presentation.ui.navigation.AppNavGraph
import com.epicmillennium.furever.presentation.ui.navigation.AppNavigationActions
import com.epicmillennium.furever.presentation.ui.navigation.BottomNavigationBar

@Composable
fun FurEverApp() {
    FurEverHomeTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            AppNavigationActions(navController)
        }

        val coroutineScope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val selectedDestination =
            navBackStackEntry?.destination?.route ?: SPLASH_SCREEN

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
        ) {
            AppNavGraph(navigationActions = navigationActions, navController = navController)
            AnimatedVisibility(visible = true) {
                BottomNavigationBar(
                    selectedDestination = selectedDestination,
                    navigateToTopLevelDestination = navigationActions::navigateTo
                )
            }
        }
    }
}