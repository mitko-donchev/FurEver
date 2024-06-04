package com.epicmillennium.furever.presentation.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.epicmillennium.furever.presentation.model.ProfilePictureState
import com.epicmillennium.furever.presentation.theme.cardShadowColor
import com.epicmillennium.fureverdomain.profile.DogProfile
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun SwipeCard(
    modifier: Modifier,
    dogProfile: DogProfile,
    pictureStates: List<ProfilePictureState>,
    onSwipeLeft: () -> Unit = {},
    onSwipeRight: () -> Unit = {},
    swipeThreshold: Float = 375f,
    sensitivityFactor: Float = 3f
) {
    var currentIndex by remember { mutableIntStateOf(0) }

    val density = LocalDensity.current.density
    val coroutineScope = rememberCoroutineScope()

    val offsetX = remember { Animatable(0f) }
    var dismissRight by remember { mutableStateOf(false) }
    var dismissLeft by remember { mutableStateOf(false) }

    LaunchedEffect(dismissRight) {
        if (dismissRight) {
            onSwipeRight.invoke()
            dismissRight = false
        }
    }

    LaunchedEffect(dismissLeft) {
        if (dismissLeft) {
            onSwipeLeft.invoke()
            dismissLeft = false
        }
    }

    Box(
        modifier = modifier
            .offset { IntOffset(offsetX.value.roundToInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        // Check if swipe thresholds are reached
                        when {
                            offsetX.value > swipeThreshold -> {
                                dismissRight = true
                            }

                            offsetX.value < -swipeThreshold -> {
                                dismissLeft = true
                            }
                            else -> {
                                // Animate back to the center if the swipe threshold was not reached
                                coroutineScope.launch {
                                    offsetX.animateTo(0f, tween(300))
                                }
                            }
                        }
                    }
                ) { change, dragAmount ->
                    // Update offset value
                    coroutineScope.launch {
                        val newOffset = offsetX.value + (dragAmount / density) * sensitivityFactor
                        offsetX.snapTo(newOffset)
                    }

                    // Consume the gesture change
                    if (change.positionChange() != Offset.Zero) change.consume()
                }
            }
            .graphicsLayer(
                alpha = 1f - (abs(offsetX.value) / (swipeThreshold * 4)).coerceIn(0f, 1f),
                rotationZ = offsetX.value / 50
            )
    ) {
        val cardShape = RoundedCornerShape(16.dp)
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .shadow(
                    shape = cardShape,
                    spotColor = cardShadowColor,
                    elevation = 2.dp
                )
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                //Picture
                when (val picture = pictureStates[currentIndex]) {
                    is ProfilePictureState.Loading -> {
                        Box(
                            Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is ProfilePictureState.Remote -> {
                        AsyncImage(
                            modifier = Modifier.fillMaxSize(),
                            model = picture.uri,
                            contentScale = ContentScale.Crop,
                            contentDescription = "Dog pictures"
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ),
                                startY = 1200f
                            )
                        )
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = dogProfile.name,
                        style = TextStyle(color = Color.White, fontSize = 16.sp)
                    )
                }
            }
        }
    }
}