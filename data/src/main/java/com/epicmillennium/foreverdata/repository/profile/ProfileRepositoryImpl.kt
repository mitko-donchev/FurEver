package com.epicmillennium.foreverdata.repository.profile

import com.epicmillennium.fureverdomain.profile.AdoptionStatus
import com.epicmillennium.fureverdomain.profile.DogProfile
import com.epicmillennium.fureverdomain.profile.Gender
import com.epicmillennium.fureverdomain.profile.ProfileRepository
import com.epicmillennium.fureverdomain.profile.UserProfile
import java.time.LocalDate

class ProfileRepositoryImpl(
    private val profileRemoteDataSource: ProfileRemoteDataSource
) : ProfileRepository {

    override suspend fun addUserProfile(
        userId: String,
        name: String,
        birthdate: LocalDate,
        location: String,
        bio: String,
        picture: String
    ) {
        profileRemoteDataSource.addUserProfile(userId, name, birthdate, location, bio, picture)
    }

    override suspend fun getUserProfile(): UserProfile = profileRemoteDataSource.getUserProfile()

    override suspend fun updateUserProfile(
        name: String?,
        birthDate: LocalDate?,
        location: String,
        bio: String,
        picture: String?
    ) {
        profileRemoteDataSource.updateUserProfile(name, birthDate, location, bio, picture)
    }

    override suspend fun addDogProfile(
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
    ) {
        profileRemoteDataSource.addDogProfile(
            id,
            name,
            age,
            breed,
            gender,
            size,
            color,
            vaccinated,
            neutered,
            healthStatus,
            temperament,
            goodWithKids,
            goodWithOtherDogs,
            goodWithCats,
            activityLevel,
            adoptionFee,
            adoptionStatus,
            description,
            photos,
            location,
            arrivalDate,
            pendingAdoptersCount,
            pendingAdopters
        )
    }

    override suspend fun updateDogProfile(
        id: String,
        name: String?,
        age: Int?,
        breed: String?,
        gender: Gender?,
        size: String?,
        color: String?,
        vaccinated: Boolean?,
        neutered: Boolean?,
        healthStatus: String?,
        temperament: String?,
        goodWithKids: Boolean?,
        goodWithOtherDogs: Boolean?,
        goodWithCats: Boolean?,
        activityLevel: String?,
        adoptionFee: Double?,
        adoptionStatus: AdoptionStatus?,
        description: String?,
        photos: List<String>,
        location: String?,
        arrivalDate: String?,
        pendingAdoptersCount: Int?,
        pendingAdopters: List<String>?
    ) {
        profileRemoteDataSource.updateDogProfile(
            id,
            name,
            age,
            breed,
            gender,
            size,
            color,
            vaccinated,
            neutered,
            healthStatus,
            temperament,
            goodWithKids,
            goodWithOtherDogs,
            goodWithCats,
            activityLevel,
            adoptionFee,
            adoptionStatus,
            description,
            photos,
            location,
            arrivalDate,
            pendingAdoptersCount,
            pendingAdopters
        )
    }

    override suspend fun likeDogProfile(dogProfile: DogProfile): String? =
        profileRemoteDataSource.likeDogProfile(dogProfile)

    override suspend fun passDogProfile(dogProfile: DogProfile) =
        profileRemoteDataSource.passDogProfile(dogProfile)

    override suspend fun getDogProfiles(): List<DogProfile> =
        profileRemoteDataSource.getDogProfiles()
}