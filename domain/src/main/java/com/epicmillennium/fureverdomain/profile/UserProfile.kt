package com.epicmillennium.fureverdomain.profile

import java.time.LocalDate

data class UserProfile(
    val id: String = "",
    val name: String = "",
    val birthDate: LocalDate,
    val location: String,
    val bio: String = "",
    val picture: String
)
