package com.epicmillennium.furever.presentation.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.epicmillennium.furever.presentation.theme.ClearRippleTheme
import com.epicmillennium.furever.presentation.theme.cardShadowColor

@Composable
fun RoundedIconButtonWithShadow(
    painter: Painter,
    contentDescription: String,
    onClick: () -> Unit,
) {
    CompositionLocalProvider(
        LocalRippleTheme provides ClearRippleTheme
    ) {
        IconButton(
            modifier = Modifier
                .size(64.dp)
                .shadow(
                    shape = CircleShape,
                    spotColor = cardShadowColor,
                    elevation = 2.dp
                ),
            onClick = onClick
        ) {
            Icon(
                painter = painter,
                contentDescription = contentDescription,
                tint = Color.Unspecified
            )
        }
    }
}