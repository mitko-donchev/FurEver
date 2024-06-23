package com.epicmillennium.furever.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.epicmillennium.furever.R
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

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(R.drawable.background),
                    contentDescription = "background_image",
                    contentScale = ContentScale.FillBounds
                )
                Scaffold(
                    containerColor = Color.Transparent,
                    bottomBar = {
                        AnimatedVisibility(visible = selectedDestination != SPLASH_SCREEN) {
                            BottomNavigationBar(
                                selectedDestination = selectedDestination,
                                navigateToTopLevelDestination = navigationActions::navigateTo
                            )
                        }
                    }
                ) {
                    AppNavGraph(
                        navigationActions = navigationActions,
                        navController = navController,
                        modifier = Modifier.padding(it),
                        startDestination = selectedDestination
                    )
                }
            }
        }
    }
}