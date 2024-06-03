package com.epicmillennium.fureverdomain.usecase

import com.epicmillennium.fureverdomain.adoption.Adoption
import com.epicmillennium.fureverdomain.adoption.AdoptionRepository
import com.epicmillennium.fureverdomain.profile.DogProfile
import com.epicmillennium.fureverdomain.profile.ProfileRepository

class LikeDogUseCase(
    private val profileRepository: ProfileRepository,
    private val adoptionRepository: AdoptionRepository
) {

    suspend operator fun invoke(profile: DogProfile): Result<Adoption?> = Result.runCatching {
        profileRepository.likeDogProfile(profile)?.let {
            adoptionRepository.getAdoption(it)
        }
    }
}