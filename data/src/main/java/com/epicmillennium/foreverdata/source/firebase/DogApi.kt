package com.epicmillennium.foreverdata.source.firebase

import com.epicmillennium.foreverdata.source.firebase.extension.getTaskResult
import com.epicmillennium.foreverdata.source.firebase.model.FirestoreAdoptionStatus
import com.epicmillennium.foreverdata.source.firebase.model.FirestoreDog
import com.epicmillennium.foreverdata.source.firebase.model.FirestoreDogProperties
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

object DogApi {

    private const val DOGS = "dogs"

    suspend fun createDog(
        dogId: String,
        name: String,
        age: Int,
        breed: String,
        isMale: Boolean,
        size: String,
        color: String,
        vaccinated: Boolean,
        neutered: Boolean,
        healthStatus: String,
        temperament: String,
        goodWithKids: Boolean,
        goodWithOtherDogs: Boolean,
        goodWithCats: Boolean,
        activityLevel: String,
        adoptionFee: Double,
        adoptionStatus: FirestoreAdoptionStatus,
        description: String,
        location: String,
        arrivalDate: String,
        pendingAdoptersCount: Int
    ) {
        val dog = FirestoreDog(
            name = name,
            age = age,
            breed = breed,
            male = isMale,
            size = size,
            color = color,
            vaccinated = vaccinated,
            neutered = neutered,
            healthStatus = healthStatus,
            temperament = temperament,
            goodWithKids = goodWithKids,
            goodWithOtherDogs = goodWithOtherDogs,
            goodWithCats = goodWithCats,
            activityLevel = activityLevel,
            adoptionFee = adoptionFee,
            adoptionStatus = adoptionStatus,
            description = description,
            pictures = emptyList(),
            location = location,
            arrivalDate = arrivalDate,
            pendingAdoptersCount = pendingAdoptersCount,
            pendingAdopters = emptyList()
        )
        FirebaseFirestore.getInstance().collection(DOGS).document(dogId).set(dog).getTaskResult()
    }

    suspend fun getDog(dogId: String): FirestoreDog? {
        val snapshot =
            FirebaseFirestore.getInstance().collection(DOGS).document(dogId).get().getTaskResult()
        return snapshot.toObject<FirestoreDog>()
    }

    suspend fun updateDog(
        dogId: String,
        age: Int,
        breed: String,
        isMale: Boolean,
        size: String,
        color: String,
        vaccinated: Boolean,
        neutered: Boolean,
        healthStatus: String,
        temperament: String,
        goodWithKids: Boolean,
        goodWithOtherDogs: Boolean,
        goodWithCats: Boolean,
        activityLevel: String,
        adoptionFee: Double,
        adoptionStatus: FirestoreAdoptionStatus,
        description: String,
        location: String,
        arrivalDate: String,
        pendingAdoptersCount: Int,
        pendingAdopters: List<String>
    ) {
        val data = mapOf(
            FirestoreDogProperties.age to age,
            FirestoreDogProperties.breed to breed,
            FirestoreDogProperties.isMale to isMale,
            FirestoreDogProperties.size to size,
            FirestoreDogProperties.color to color,
            FirestoreDogProperties.vaccinated to vaccinated,
            FirestoreDogProperties.neutered to neutered,
            FirestoreDogProperties.healthStatus to healthStatus,
            FirestoreDogProperties.temperament to temperament,
            FirestoreDogProperties.goodWithKids to goodWithKids,
            FirestoreDogProperties.goodWithOtherDogs to goodWithOtherDogs,
            FirestoreDogProperties.goodWithCats to goodWithCats,
            FirestoreDogProperties.activityLevel to activityLevel,
            FirestoreDogProperties.adoptionFee to adoptionFee,
            FirestoreDogProperties.adoptionStatus to adoptionStatus,
            FirestoreDogProperties.description to description,
            FirestoreDogProperties.location to location,
            FirestoreDogProperties.arrivalDate to arrivalDate,
            FirestoreDogProperties.pendingAdoptersCount to pendingAdoptersCount,
            FirestoreDogProperties.pendingAdopters to pendingAdopters,
        )
        FirebaseFirestore.getInstance().collection(DOGS).document(dogId).update(data)
            .getTaskResult()
    }


    suspend fun updateDogPictures(dogId: String, pictures: List<String>) {
        val data = mapOf(
            FirestoreDogProperties.pictures to pictures
        )
        FirebaseFirestore.getInstance().collection(DOGS).document(dogId).update(data)
            .getTaskResult()
    }
}