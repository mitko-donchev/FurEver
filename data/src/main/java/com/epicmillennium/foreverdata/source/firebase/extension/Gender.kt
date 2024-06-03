package com.epicmillennium.foreverdata.source.firebase.extension

import com.epicmillennium.fureverdomain.profile.Gender

fun Gender.toBoolean() = when (this) {
    Gender.MALE -> true
    Gender.FEMALE -> false
}