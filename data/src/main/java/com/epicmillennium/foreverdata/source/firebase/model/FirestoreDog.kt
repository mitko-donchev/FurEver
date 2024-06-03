package com.epicmillennium.foreverdata.source.firebase.model

import com.google.firebase.firestore.DocumentId

data class FirestoreDog(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val age: Int = 0,
    val breed: String = "Unknown",
    @field:JvmField
    val male: Boolean? = null,
    val size: String = "",
    val color: String = "",
    val vaccinated: Boolean = false,
    val neutered: Boolean = false,
    val healthStatus: String = "",
    val temperament: String = "",
    val goodWithKids: Boolean = false,
    val goodWithOtherDogs: Boolean = false,
    val goodWithCats: Boolean = false,
    val activityLevel: String = "",
    val adoptionFee: Double = 0.0,
    val adoptionStatus: FirestoreAdoptionStatus? = null,
    val description: String = "",
    val pictures: List<String> = emptyList(),
    val location: String = "",
    val arrivalDate: String = "",
    val pendingAdoptersCount: Int = 0,
    val pendingAdopters: List<String> = emptyList()
)

object FirestoreDogProperties {
    // The property values should reflect the actual name of the data class
    // property they are referencing. This is done so in order to keep track
    // of the property names from a single place.
    const val age = "age"
    const val breed = "breed"
    const val isMale = "isMale"
    const val size = "size"
    const val color = "color"
    const val vaccinated = "vaccinated"
    const val neutered = "neutered"
    const val healthStatus = "healthStatus"
    const val temperament = "temperament"
    const val goodWithKids = "goodWithKids"
    const val goodWithOtherDogs = "goodWithOtherDogs"
    const val goodWithCats = "goodWithCats"
    const val activityLevel = "activityLevel"
    const val adoptionFee = "adoptionFee"
    const val adoptionStatus = "adoptionStatus"
    const val description = "description"
    const val pictures = "pictures"
    const val location = "location"
    const val arrivalDate = "arrivalDate"
    const val pendingAdoptersCount = "pendingAdoptersCount"
    const val pendingAdopters = "pendingAdopters"
}
