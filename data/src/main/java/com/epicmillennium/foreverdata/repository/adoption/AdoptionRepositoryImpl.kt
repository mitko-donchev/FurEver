package com.epicmillennium.foreverdata.repository.adoption

import com.epicmillennium.fureverdomain.adoption.Adoption
import com.epicmillennium.fureverdomain.adoption.AdoptionRepository

class AdoptionRepositoryImpl(
    private val adoptionRemoteDataSource: AdoptionRemoteDataSource
) : AdoptionRepository {

    override suspend fun getAdoptions(): List<Adoption> = adoptionRemoteDataSource.getAdoptions()

    override suspend fun getAdoption(id: String): Adoption = adoptionRemoteDataSource.getAdoption(id)
}