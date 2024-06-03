package com.epicmillennium.fureverdomain.usecase

import com.epicmillennium.fureverdomain.profile.DogProfile
import com.epicmillennium.fureverdomain.profile.ProfileRepository

class GetDogProfilesUseCase(private val profileRepository: ProfileRepository) {

    suspend operator fun invoke(): Result<List<DogProfile>> =
        Result.runCatching { profileRepository.getDogProfiles() }
}