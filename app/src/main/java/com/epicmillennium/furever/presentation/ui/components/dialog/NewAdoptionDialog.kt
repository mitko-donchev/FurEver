package com.epicmillennium.furever.presentation.ui.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.epicmillennium.furever.presentation.model.ProfilePictureState
import com.epicmillennium.furever.presentation.ui.home.NewAdoptionView

@Composable
fun NewAdoptionDialog(
    pictureStates: List<ProfilePictureState>,
    onSendMessage: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        NewAdoptionView(
            pictureStates = pictureStates,
            onSendMessage = onSendMessage,
            onDismissRequest = onDismissRequest
        )
    }
}