package com.epicmillennium.furever.presentation.model

import androidx.compose.runtime.Immutable
import com.epicmillennium.fureverdomain.adoption.Adoption

@Immutable
data class AdoptionState(val adoption: Adoption, val pictureState: ProfilePictureState)