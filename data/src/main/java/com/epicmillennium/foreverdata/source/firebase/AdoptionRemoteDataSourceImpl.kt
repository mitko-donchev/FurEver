package com.epicmillennium.foreverdata.source.firebase

import com.epicmillennium.foreverdata.repository.adoption.AdoptionRemoteDataSource
import com.epicmillennium.foreverdata.source.firebase.extension.toAdoptionStatus
import com.epicmillennium.foreverdata.source.firebase.extension.toLocalDate
import com.epicmillennium.foreverdata.source.firebase.model.FirestoreAdoption
import com.epicmillennium.fureverdomain.adoption.Adoption
import com.epicmillennium.fureverdomain.profile.AdoptionStatus
import com.epicmillennium.fureverdomain.profile.DogProfile
import com.epicmillennium.fureverdomain.profile.Gender
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class AdoptionRemoteDataSourceImpl : AdoptionRemoteDataSource {

    override suspend fun getAdoptions(): List<Adoption> = coroutineScope {
        val apiMatches = AdoptionApi.getAdoptions()
        val matches = apiMatches.map { async { it.toModel() } }.awaitAll()
        matches.filterNotNull()
    }

    private suspend fun FirestoreAdoption.toModel(): Adoption? {
        val dogId = this.usersAdoptions.firstOrNull() ?: return null
        val dog = DogApi.getDog(dogId) ?: return null
        return Adoption(
            this.id,
            DogProfile(
                dogId,
                dog.name,
                dog.age,
                dog.breed,
                if (dog.male == true) Gender.MALE else Gender.FEMALE,
                dog.size,
                dog.color,
                dog.vaccinated,
                dog.neutered,
                dog.healthStatus,
                dog.temperament,
                dog.goodWithKids,
                dog.goodWithOtherDogs,
                dog.goodWithCats,
                dog.activityLevel,
                dog.adoptionFee,
                dog.adoptionStatus?.toAdoptionStatus() ?: AdoptionStatus.FOR_ADOPTION,
                dog.description,
                dog.pictures,
                dog.location,
                dog.arrivalDate,
                dog.pendingAdoptersCount,
                dog.pendingAdopters,
            ),
            this.timestamp!!.toLocalDate(),
            this.lastMessage
        )
    }

    override suspend fun getAdoption(id: String): Adoption = AdoptionApi.getAdoption(id).toModel()!!
}