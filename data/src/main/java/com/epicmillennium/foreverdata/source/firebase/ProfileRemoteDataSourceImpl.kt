package com.epicmillennium.foreverdata.source.firebase

import com.epicmillennium.foreverdata.repository.profile.ProfileRemoteDataSource
import com.epicmillennium.foreverdata.source.firebase.extension.toAdoptionStatus
import com.epicmillennium.foreverdata.source.firebase.extension.toFirestoreAdoptionStatus
import com.epicmillennium.foreverdata.source.firebase.extension.toLocalDate
import com.epicmillennium.foreverdata.source.firebase.extension.toTimestamp
import com.epicmillennium.foreverdata.source.firebase.model.FirestoreUser
import com.epicmillennium.fureverdomain.profile.AdoptionStatus
import com.epicmillennium.fureverdomain.profile.DogProfile
import com.epicmillennium.fureverdomain.profile.Gender
import com.epicmillennium.fureverdomain.profile.UserProfile
import java.time.LocalDate

class ProfileRemoteDataSourceImpl : ProfileRemoteDataSource {

    /**
    These properties are stored due to the particularities of Firebase.
    With a traditional backend application, for queries that need the data of the current user,
    we would already have access to that data inside of the backend so there would be no need to pass it,
    however, we can't do this with Firebase restrictions, so in order to avoid the cost of performing an extra query
    we just store it in memory so that it stays available when needed.
     */
    private var currentUser: FirestoreUser? = null

    private suspend fun getCurrentUser(): FirestoreUser {
        return currentUser ?: run {
            val user = UserApi.getUser(AuthApi.userId!!)!!
            currentUser = user
            user
        }
    }

    override suspend fun addUserProfile(
        userId: String,
        name: String,
        birthdate: LocalDate,
        location: String,
        bio: String,
        picture: String
    ) {
        UserApi.createUser(
            userId,
            name,
            birthdate.toTimestamp(),
            location,
            bio
        )
    }

    override suspend fun getUserProfile(): UserProfile {
        val currentUser = getCurrentUser()
        return UserProfile(
            currentUser.id,
            currentUser.name,
            currentUser.birthDate!!.toLocalDate(),
            currentUser.location,
            currentUser.bio,
            currentUser.picture
        )
    }

    override suspend fun updateUserProfile(
        name: String?,
        birthDate: LocalDate?,
        location: String,
        bio: String,
        picture: String?
    ) {
        UserApi.updateUser(bio, location)
        currentUser = currentUser?.copy(bio = bio, location = location)
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
        DogApi.createDog(
            id,
            name,
            age,
            breed,
            gender == Gender.MALE,
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
            adoptionStatus.toFirestoreAdoptionStatus(),
            description,
            location,
            arrivalDate,
            pendingAdoptersCount,
        )
    }

    override suspend fun updateDogProfile(
        dogId: String,
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
        DogApi.updateDogPictures(dogId, photos)
    }

    override suspend fun likeDogProfile(dogProfile: DogProfile): String? =
        UserApi.swipeDog(dogProfile.id, true)

    override suspend fun passDogProfile(dogProfile: DogProfile) {
        UserApi.swipeDog(dogProfile.id, false)
    }

    override suspend fun getDogProfiles(): List<DogProfile> {
        val users = UserApi.getCompatibleDogs(getCurrentUser())
        return users.map { dog ->
            DogProfile(
                dog.id,
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
            )
        }
    }
}