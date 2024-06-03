package com.epicmillennium.furever.presentation.ui.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.epicmillennium.furever.R
import com.epicmillennium.furever.utils.getImageResource
import com.epicmillennium.furever.utils.getListOfImages
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateToHome: () -> Unit) {
    var currentFrame by remember { mutableIntStateOf(0) }
    var repeatCount by remember { mutableIntStateOf(0) }

    val totalFrames = getListOfImages().size
    val maxRepeats = 5 // Number of times to repeat the animation

    LaunchedEffect(Unit) {
        while (repeatCount < maxRepeats) {
            delay(25)
            currentFrame = (currentFrame + 1) % totalFrames
            if (currentFrame == 0) {
                repeatCount++
            }
        }

        navigateToHome()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillBounds
            ), contentAlignment = Alignment.Center
    ) {
        if (repeatCount < maxRepeats) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .offset(y = (-124).dp),
                painter = painterResource(id = R.drawable.logo_fur),
                contentDescription = stringResource(id = R.string.app_name),
                tint = Color.Unspecified
            )
            val imageResource = getImageResource(currentFrame)
            Image(painter = painterResource(id = imageResource), contentDescription = null)
        }
    }
}