package com.epicmillennium.fureverdomain.usecase

import com.epicmillennium.fureverdomain.picture.LocalPicture
import com.epicmillennium.fureverdomain.picture.Picture
import com.epicmillennium.fureverdomain.picture.PictureRepository
import com.epicmillennium.fureverdomain.picture.RemotePicture
import com.epicmillennium.fureverdomain.profile.ProfileRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class UpdatePictureUseCase(
    private val profileRepository: ProfileRepository,
    private val pictureRepository: PictureRepository
) {
    suspend operator fun invoke(picture: Picture): Result<String> {
        return Result.runCatching {
            val currentProfile = profileRepository.getUserProfile()

            //This is a list of the pictures that were already uploaded but that have been removed from the profile.
            val picturesToDelete: String = currentProfile.picture

            coroutineScope {
                val pictureDeletionResult = async {
                    pictureRepository.deletePicture(picturesToDelete)
                }

                val pictureUploadResult = async {
                    val pictureNameResult =
                        async {
                            when (picture) {
                                //If the picture was already uploaded, simply return its file name.
                                is RemotePicture -> picture.filename
                                //Otherwise upload it and return it's new file name
                                is LocalPicture -> pictureRepository.addPicture(picture.uri)
                            }
                        }
                    val pictureName = pictureNameResult.await()
                    profileRepository.updateUserProfile(picture = pictureName, location = currentProfile.location, bio = currentProfile.bio)
                    pictureName
                }

                pictureDeletionResult.await()
                pictureUploadResult.await()
            }

        }
    }
}