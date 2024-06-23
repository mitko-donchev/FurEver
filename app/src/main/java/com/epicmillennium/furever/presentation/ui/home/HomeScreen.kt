package com.epicmillennium.furever.presentation.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen() {

    val homeViewModel = hiltViewModel<HomeViewModel>()
    val uiState by homeViewModel.uiState.collectAsState()

    HomeView(
        uiState = uiState,
        recoverLastProfile = homeViewModel::recoverLastProfile,
        removeLastProfile = homeViewModel::removeLastProfile,
        fetchDogProfiles = homeViewModel::fetchDogProfiles,
        swipeDog = homeViewModel::swipeDog,
        onCloseDialog = homeViewModel::closeDialog,
        onSendMessage = homeViewModel::sendMessage
    )
}