package com.epicmillennium.furever.presentation.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.epicmillennium.furever.presentation.theme.ClearRippleTheme
import com.epicmillennium.furever.presentation.theme.gradientStrongNavBarIconColor
import com.epicmillennium.furever.presentation.theme.gradientWeekNavBarIconColor

@Composable
fun BottomNavigationBar(
    selectedDestination: String,
    navigateToTopLevelDestination: (AppTopLevelDestination) -> Unit
) {
    CompositionLocalProvider(
        LocalRippleTheme provides ClearRippleTheme
    ) {
        NavigationBar(Modifier.fillMaxWidth(), containerColor = Color.Transparent) {
            TOP_LEVEL_DESTINATIONS.forEach { destination ->
                val isSelected = selectedDestination == destination.route
                NavigationBarItem(
                    selected = isSelected,
                    onClick = { navigateToTopLevelDestination(destination) },
                    icon = {
                        BadgedBox(badge = {
                            if (destination.badgeCount != null) {
                                Badge {
                                    Text(text = destination.badgeCount.toString())
                                }
                            } else if (destination.hasNews) {
                                Badge()
                            }
                        }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = if (isSelected) destination.selectedIcon else destination.unselectedIcon),
                                contentDescription = stringResource(id = destination.iconTextId),
                                tint = Color.Unspecified
                            )
                        }
                    },
                    label = {
                        val textStyle = if (isSelected) {
                            TextStyle(color = gradientWeekNavBarIconColor)
                        } else {
                            TextStyle(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        gradientWeekNavBarIconColor,
                                        gradientStrongNavBarIconColor
                                    ),
                                    startY = 10f,
                                )
                            )
                        }

                        Text(
                            text = stringResource(id = destination.iconTextId),
                            style = textStyle
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    ),
                )
            }
        }
    }
}