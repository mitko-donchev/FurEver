package com.epicmillennium.fureverdomain.adoption

import com.epicmillennium.fureverdomain.profile.DogProfile
import java.time.LocalDate

data class Adoption(
    val id: String,
    val dogProfile: DogProfile,
    val creationDate: LocalDate,
    val lastMessage: String?
)