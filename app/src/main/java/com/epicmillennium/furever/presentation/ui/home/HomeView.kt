package com.epicmillennium.furever.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.epicmillennium.furever.R
import com.epicmillennium.furever.presentation.ui.components.GradientButton
import com.epicmillennium.furever.presentation.ui.components.RoundedIconButtonWithShadow
import com.epicmillennium.furever.presentation.ui.components.SwipeCard
import com.epicmillennium.furever.presentation.ui.components.dialog.NewAdoptionDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeView(
    uiState: HomeViewState,
    recoverLastProfile: () -> Unit,
    removeLastProfile: () -> Unit,
    fetchDogProfiles: () -> Unit,
    swipeDog: (DogProfileState, Boolean) -> Unit,
    onSendMessage: (String, String) -> Unit,
    onCloseDialog: () -> Unit
) {
    val scope = rememberCoroutineScope()

    when (uiState.dialogState) {
        is HomeViewDialogState.NewOngoingAdoptionDialog -> {
            NewAdoptionDialog(
                pictureStates = uiState.dialogState.pictureStates,
                onSendMessage = { onSendMessage(uiState.dialogState.adoption.id, it) },
                onDismissRequest = onCloseDialog
            )
        }

        else -> {}
    }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.weight(1f),
                    painter = painterResource(R.drawable.logo_fur),
                    contentDescription = stringResource(id = R.string.app_name),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState.contentState) {
                is HomeViewContentState.Error -> {
                    Spacer(Modifier.weight(1f))
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = uiState.contentState.message,
                        color = Color.Gray,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(12.dp))
                    GradientButton(onClick = {
                        scope.launch {
                            delay(200)
                            fetchDogProfiles()
                        }
                    }) {
                        Text(stringResource(id = R.string.retry))
                    }
                    Spacer(Modifier.weight(1f))
                }

                HomeViewContentState.Loading -> {
                    Spacer(Modifier.weight(1f))
                    CircularProgressIndicator()
                    Spacer(Modifier.weight(1f))
                }

                is HomeViewContentState.Success -> {

                    val localDensity = LocalDensity.current
                    var buttonRowHeightDp by remember { mutableStateOf(0.dp) }

                    Box(Modifier.fillMaxSize()) {
                        Text(
                            text = stringResource(id = R.string.no_more_dog),
                            color = Color.Gray,
                            fontSize = 20.sp,
                            modifier = Modifier.align(Alignment.Center)
                        )

                        uiState.contentState.dogProfileStates.forEachIndexed { _, profileState ->
                            SwipeCard(
                                modifier = Modifier.padding(bottom = buttonRowHeightDp.plus(16.dp)),
                                dogProfile = profileState.dogProfile,
                                pictureStates = profileState.pictureStates,
                                onSwipeRight = {
                                    swipeDog(
                                        profileState,
                                        true
                                    )
                                    removeLastProfile()
                                },
                                onSwipeLeft = {
                                    swipeDog(
                                        profileState,
                                        false
                                    )
                                    removeLastProfile()
                                },
                            )
                        }

                        if (uiState.contentState.dogProfileStates.isNotEmpty()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter)
                                    .padding(8.dp)
                                    .onGloballyPositioned { coordinates ->
                                        buttonRowHeightDp =
                                            with(localDensity) { coordinates.size.height.toDp() }
                                    },
                                horizontalArrangement = Arrangement.Center
                            ) {
                                RoundedIconButtonWithShadow(
                                    painterResource(id = R.drawable.ic_skip_dog),
                                    "Skip dog profile",
                                    onClick = {
                                        swipeDog(
                                            uiState.contentState.dogProfileStates.first(),
                                            false
                                        )
                                        removeLastProfile()
                                    }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                RoundedIconButtonWithShadow(
                                    painterResource(id = R.drawable.ic_return_last_dog),
                                    "Return last dog profile",
                                    onClick = {
                                        recoverLastProfile()
                                    }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                RoundedIconButtonWithShadow(
                                    painterResource(id = R.drawable.ic_like_dog),
                                    "Like dog profile",
                                    onClick = {
                                        swipeDog(
                                            uiState.contentState.dogProfileStates.first(),
                                            true
                                        )
                                        removeLastProfile()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}