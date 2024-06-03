package com.epicmillennium.fureverdomain.usecase

import com.epicmillennium.fureverdomain.profile.ProfileRepository
import com.epicmillennium.fureverdomain.profile.UserProfile

class GetUserProfileUseCase(private val profileRepository: ProfileRepository) {

    suspend operator fun invoke(): Result<UserProfile> =
        Result.runCatching { profileRepository.getUserProfile() }
}