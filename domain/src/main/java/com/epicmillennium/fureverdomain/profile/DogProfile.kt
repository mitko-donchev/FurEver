package com.epicmillennium.fureverdomain.profile


class DogProfile(
    val id: String,
    val name: String,
    val age: Int,
    val breed: String,
    val gender: Gender,
    val size: String,
    val color: String,
    val vaccinated: Boolean,
    val neutered: Boolean,
    val healthStatus: String,
    val temperament: String,
    val goodWithKids: Boolean,
    val goodWithOtherDogs: Boolean,
    val goodWithCats: Boolean,
    val activityLevel: String,
    val adoptionFee: Double,
    val adoptionStatus: AdoptionStatus,
    val description: String,
    val pictures: List<String>,
    val location: String,
    val arrivalDate: String,
    val pendingAdoptersCount: Int,
    val pendingAdopters: List<String>
)