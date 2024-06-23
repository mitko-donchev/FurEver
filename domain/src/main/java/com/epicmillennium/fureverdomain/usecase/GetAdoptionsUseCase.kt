package com.epicmillennium.fureverdomain.usecase

import com.epicmillennium.fureverdomain.adoption.Adoption
import com.epicmillennium.fureverdomain.adoption.AdoptionRepository

class GetAdoptionsUseCase(private val adoptionRepository: AdoptionRepository) {

    suspend operator fun invoke(): Result<List<Adoption>> =
        Result.runCatching { adoptionRepository.getAdoptions() }
}