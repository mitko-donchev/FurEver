package com.epicmillennium.foreverdata.source.mock

import com.epicmillennium.foreverdata.repository.adoption.AdoptionRemoteDataSource
import com.epicmillennium.fureverdomain.adoption.Adoption
import com.epicmillennium.fureverdomain.profile.AdoptionStatus
import com.epicmillennium.fureverdomain.profile.DogProfile
import com.epicmillennium.fureverdomain.profile.Gender
import kotlinx.coroutines.delay
import java.time.LocalDate

class AdoptionRemoteDataSourceMockImpl : AdoptionRemoteDataSource {
    private val ongoingAdoptionsList = listOf(
        Adoption(
            "1",
            DogProfile(
                id = "1",
                name = "Tor",
                age = 2,
                breed = "Siberian Husky",
                gender = Gender.MALE,
                size = "Large",
                color = "Gray and White",
                vaccinated = true,
                neutered = false,
                healthStatus = "Healthy",
                temperament = "Energetic",
                goodWithKids = true,
                goodWithOtherDogs = true,
                goodWithCats = false,
                activityLevel = "High",
                adoptionFee = 270.0,
                adoptionStatus = AdoptionStatus.FOR_ADOPTION,
                description = "Very energetic and loves to run",
                pictures = listOf("dog_9.png"),
                location = "Seattle, WA",
                arrivalDate = "2023-02-01",
                pendingAdoptersCount = 2,
                pendingAdopters = listOf("Kim Wilson", "James Taylor")
            ),
            LocalDate.of(22, 12, 12),
            "Hey!"
        ),
        Adoption(
            "2",
            DogProfile(
                id = "2",
                name = "Buddy",
                age = 3,
                breed = "Golden Retriever",
                gender = Gender.MALE,
                size = "Large",
                color = "Golden",
                vaccinated = true,
                neutered = true,
                healthStatus = "Healthy",
                temperament = "Friendly",
                goodWithKids = true,
                goodWithOtherDogs = true,
                goodWithCats = true,
                activityLevel = "High",
                adoptionFee = 200.0,
                adoptionStatus = AdoptionStatus.FOR_ADOPTION,
                description = "Loves to play and run",
                pictures = listOf("dog_1.png"),
                location = "New York, NY",
                arrivalDate = "2023-01-10",
                pendingAdoptersCount = 2,
                pendingAdopters = listOf("John Doe", "Jane Smith")
            ),
            LocalDate.of(23, 12, 12),
            "Hey!"
        ),
        Adoption(
            "3",
            DogProfile(
                id = "3",
                name = "Molly",
                age = 5,
                breed = "Beagle",
                gender = Gender.FEMALE,
                size = "Medium",
                color = "Tricolor",
                vaccinated = true,
                neutered = true,
                healthStatus = "Healthy",
                temperament = "Calm",
                goodWithKids = true,
                goodWithOtherDogs = true,
                goodWithCats = false,
                activityLevel = "Medium",
                adoptionFee = 150.0,
                adoptionStatus = AdoptionStatus.IN_PROGRESS,
                description = "Enjoys long walks",
                pictures = listOf("dog_2.png"),
                location = "Los Angeles, CA",
                arrivalDate = "2022-12-15",
                pendingAdoptersCount = 1,
                pendingAdopters = listOf("Alice Johnson")
            ),
            LocalDate.of(22, 12, 12),
            "Hey!"
        ),
    )

    override suspend fun getAdoptions(): List<Adoption> {
        delay(1000)
        return ongoingAdoptionsList
    }

    override suspend fun getAdoption(id: String): Adoption {
        delay(1000)
        return ongoingAdoptionsList.first()
    }
}