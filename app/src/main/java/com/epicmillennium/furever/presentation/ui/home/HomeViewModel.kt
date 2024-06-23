package com.epicmillennium.furever.presentation.ui.home

import android.net.Uri
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epicmillennium.furever.presentation.model.ProfilePictureState
import com.epicmillennium.fureverdomain.adoption.Adoption
import com.epicmillennium.fureverdomain.profile.DogProfile
import com.epicmillennium.fureverdomain.usecase.GetDogProfilesUseCase
import com.epicmillennium.fureverdomain.usecase.GetPictureUseCase
import com.epicmillennium.fureverdomain.usecase.LikeDogUseCase
import com.epicmillennium.fureverdomain.usecase.SendMessageUseCase
import com.epicmillennium.fureverdomain.usecase.SkippedDogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDogProfilesUseCase: GetDogProfilesUseCase,
    private val likeDogProfileUseCase: LikeDogUseCase,
    private val skippedDogUseCase: SkippedDogUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val getPictureUseCase: GetPictureUseCase
) : ViewModel() {

    private lateinit var lastRemovedProfile: DogProfileState

    private val _uiState =
        MutableStateFlow(HomeViewState(HomeViewDialogState.NoDialog, HomeViewContentState.Loading))

    val uiState = _uiState.asStateFlow()

    init {
        fetchDogProfiles()
    }

    fun closeDialog() {
        _uiState.update {
            it.copy(dialogState = HomeViewDialogState.NoDialog)
        }
    }

    fun sendMessage(matchId: String, text: String) = viewModelScope.launch {
        sendMessageUseCase(matchId, text)
    }

    fun swipeDog(profileState: DogProfileState, isLike: Boolean) = viewModelScope.launch {
        if (isLike) {
            likeDogProfileUseCase(profileState.dogProfile).onSuccess { result ->
                result?.let { adoption ->
                    _uiState.update {
                        it.copy(
                            dialogState = HomeViewDialogState.NewOngoingAdoptionDialog(
                                adoption,
                                profileState.pictureStates
                            )
                        )
                    }
                }
            }
        } else {
            skippedDogUseCase(profileState.dogProfile)
        }
    }

    fun recoverLastProfile() {
        if (::lastRemovedProfile.isInitialized) {
            _uiState.update {
                if (it.contentState is HomeViewContentState.Success) {
                    if (lastRemovedProfile == it.contentState.dogProfileStates.last()) return@update it

                    it.copy(
                        contentState = it.contentState.copy(
                            dogProfileStates = it.contentState.dogProfileStates + lastRemovedProfile
                        )
                    )
                } else it
            }
        }
    }

    fun removeLastProfile() {
        _uiState.update {
            if (it.contentState is HomeViewContentState.Success) {
                lastRemovedProfile = it.contentState.dogProfileStates.last()

                it.copy(
                    contentState = it.contentState.copy(
                        dogProfileStates = it.contentState.dogProfileStates.dropLast(
                            1
                        )
                    )
                )
            } else it
        }
    }

    fun fetchDogProfiles() = viewModelScope.launch {
        _uiState.update { it.copy(contentState = HomeViewContentState.Loading) }
        getDogProfilesUseCase().fold({ profiles ->
            _uiState.update { homeViewState ->
                homeViewState.copy(contentState = HomeViewContentState.Success(dogProfileStates = profiles.map { profile ->
                    DogProfileState(
                        profile,
                        profile.pictures.map { ProfilePictureState.Loading(it) })
                }))
            }

            profiles.forEach { dogProfile ->
                loadProfilePictures(dogProfile.id, dogProfile.pictures)
            }

        }, { error ->
            _uiState.update {
                it.copy(
                    contentState = HomeViewContentState.Error(
                        error.message ?: ""
                    )
                )
            }
        })
    }

    private suspend fun loadProfilePictures(dogId: String, pictureNames: List<String>) {
        pictureNames.forEach { pictureName ->
            viewModelScope.launch {
                getPictureUseCase(dogId, pictureName).onSuccess { pictureUrl ->
                    updatePicturesState(dogId, pictureName, Uri.parse(pictureUrl))
                }
            }
        }
    }

    private fun updatePicturesState(dogId: String, pictureName: String, pictureUrl: Uri) {
        _uiState.update {
            if (it.contentState is HomeViewContentState.Success) {
                it.copy(contentState = it.contentState.copy(
                    dogProfileStates = it.contentState.dogProfileStates.map { profileState ->
                        if (profileState.dogProfile.id == dogId) {
                            profileState.copy(pictureStates = profileState.pictureStates.map { pictureState ->
                                if (pictureState is ProfilePictureState.Loading && pictureState.name == pictureName)
                                    ProfilePictureState.Remote(pictureUrl)
                                else pictureState
                            })
                        } else profileState
                    }
                )
                )
            } else it
        }
    }
}

@Immutable
data class HomeViewState(
    val dialogState: HomeViewDialogState,
    val contentState: HomeViewContentState
)

@Immutable
data class DogProfileState(val dogProfile: DogProfile, val pictureStates: List<ProfilePictureState>)

@Immutable
sealed class HomeViewDialogState {
    data object NoDialog : HomeViewDialogState()
    data class NewOngoingAdoptionDialog(
        val adoption: Adoption,
        val pictureStates: List<ProfilePictureState>
    ) : HomeViewDialogState()
}

@Immutable
sealed class HomeViewContentState {
    data object Loading : HomeViewContentState()
    data class Success(val dogProfileStates: List<DogProfileState>) : HomeViewContentState()
    data class Error(val message: String) : HomeViewContentState()
}