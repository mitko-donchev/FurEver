package com.epicmillennium.fureverdomain.usecase

import com.epicmillennium.fureverdomain.profile.DogProfile
import com.epicmillennium.fureverdomain.profile.ProfileRepository

class SkippedDogUseCase(private val profileRepository: ProfileRepository) {

    suspend operator fun invoke(profile: DogProfile): Result<Unit> =
        Result.runCatching { profileRepository.passDogProfile(profile) }
}