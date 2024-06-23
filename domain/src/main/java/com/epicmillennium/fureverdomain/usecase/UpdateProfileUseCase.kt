package com.epicmillennium.fureverdomain.usecase

import com.epicmillennium.fureverdomain.profile.ProfileRepository

class UpdateProfileUseCase(private val profileRepository: ProfileRepository) {

    suspend operator fun invoke(bio: String): Result<Unit> {
        return Result.runCatching {
            val profile = profileRepository.getUserProfile()
            if (profile.bio != bio) {
                profileRepository.updateUserProfile(bio = bio, location = profile.location)
            }
        }
    }
}