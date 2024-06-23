package com.epicmillennium.fureverdomain.adoption

interface AdoptionRepository {
    suspend fun getAdoptions(): List<Adoption>
    suspend fun getAdoption(id: String): Adoption
}