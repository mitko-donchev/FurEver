package com.epicmillennium.foreverdata.repository.adoption

import com.epicmillennium.fureverdomain.adoption.Adoption

interface AdoptionRemoteDataSource {
    suspend fun getAdoptions(): List<Adoption>
    suspend fun getAdoption(id: String): Adoption
}