package com.epicmillennium.fureverdomain.profile

import java.time.LocalDate

interface ProfileRepository {

    // User related
    suspend fun addUserProfile(
        userId: String,
        name: String,
        birthdate: LocalDate,
        location: String,
        bio: String,
        picture: String
    )

    suspend fun getUserProfile(): UserProfile

    suspend fun updateUserProfile(
        name: String? = null,
        birthDate: LocalDate? = null,
        location: String,
        bio: String,
        picture: String? = null
    )

    // Dod related
    suspend fun addDogProfile(
        id: String,
        name: String,
        age: Int,
        breed: String,
        gender: Gender,
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
        adoptionStatus: AdoptionStatus,
        description: String,
        photos: List<String>,
        location: String,
        arrivalDate: String,
        pendingAdoptersCount: Int,
        pendingAdopters: List<String>
    )

    suspend fun updateDogProfile(
        id: String,
        name: String? = null,
        age: Int? = null,
        breed: String? = null,
        gender: Gender? = null,
        size: String? = null,
        color: String? = null,
        vaccinated: Boolean? = null,
        neutered: Boolean? = null,
        healthStatus: String? = null,
        temperament: String? = null,
        goodWithKids: Boolean? = null,
        goodWithOtherDogs: Boolean? = null,
        goodWithCats: Boolean? = null,
        activityLevel: String? = null,
        adoptionFee: Double? = null,
        adoptionStatus: AdoptionStatus? = null,
        description: String? = null,
        photos: List<String>,
        location: String? = null,
        arrivalDate: String? = null,
        pendingAdoptersCount: Int? = null,
        pendingAdopters: List<String>? = null
    )

    suspend fun likeDogProfile(dogProfile: DogProfile): String?
    suspend fun passDogProfile(dogProfile: DogProfile)
    suspend fun getDogProfiles(): List<DogProfile>
}