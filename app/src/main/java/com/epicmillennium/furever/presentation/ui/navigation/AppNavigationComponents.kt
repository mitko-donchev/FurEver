package com.epicmillennium.furever.presentation.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.epicmillennium.furever.presentation.theme.selectedNavBarIconColor

@Composable
fun BottomNavigationBar(
    selectedDestination: String,
    navigateToTopLevelDestination: (AppTopLevelDestination) -> Unit
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
                    Text(text = stringResource(id = destination.iconTextId))
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedTextColor = selectedNavBarIconColor
                ),
            )
        }
    }
}