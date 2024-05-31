package com.epicmillennium.furever.presentation.ui.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.epicmillennium.furever.presentation.ui.mainScreen.MainScreen
import com.epicmillennium.furever.presentation.ui.navigation.AppDestinations.HOME
import com.epicmillennium.furever.presentation.ui.navigation.AppDestinations.SPLASH_SCREEN
import com.epicmillennium.furever.presentation.ui.splashScreen.SplashScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navigationActions: AppNavigationActions,
    navController: NavHostController = rememberNavController(),
    startDestination: String = SPLASH_SCREEN
) {
    val animationSpec = remember {
        keyframes {
            durationMillis = 200
            0f at 0 using FastOutSlowInEasing // Initial state (not scaled)
            0.25f at 50 using FastOutSlowInEasing
            0.50f at 100 using FastOutSlowInEasing
            0.75f at 150 using FastOutSlowInEasing
            1f at 200 // Fully scaled to 1 after 200ms
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = SPLASH_SCREEN,
            enterTransition = {
                fadeIn(animationSpec = tween(durationMillis = 1000))
            }, exitTransition = {
                fadeOut(animationSpec = tween(durationMillis = 1000))
            }
        ) {
            SplashScreen(navigateToHome = { navigationActions.navigateToHome() })
        }
        composable(
            route = HOME,
            enterTransition = {
                scaleIn(animationSpec = animationSpec, initialScale = 0f)
            },
            exitTransition = {
                fadeOut(animationSpec = tween(durationMillis = 1000))
            }
        ) {
            MainScreen()
        }
    }
}