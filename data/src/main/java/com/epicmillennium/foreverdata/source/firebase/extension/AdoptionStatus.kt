package com.epicmillennium.foreverdata.source.firebase.extension

import com.epicmillennium.foreverdata.source.firebase.model.FirestoreAdoptionStatus
import com.epicmillennium.fureverdomain.profile.AdoptionStatus

fun AdoptionStatus.toFirestoreAdoptionStatus(): FirestoreAdoptionStatus = when(this) {
    AdoptionStatus.FOR_ADOPTION -> FirestoreAdoptionStatus.forAdoption
    AdoptionStatus.IN_PROGRESS -> FirestoreAdoptionStatus.inProgress
    AdoptionStatus.ADOPTED -> FirestoreAdoptionStatus.adopted
}