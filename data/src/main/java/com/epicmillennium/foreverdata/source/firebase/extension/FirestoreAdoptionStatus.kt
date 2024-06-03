package com.epicmillennium.foreverdata.source.firebase.extension

import com.epicmillennium.foreverdata.source.firebase.model.FirestoreAdoptionStatus
import com.epicmillennium.fureverdomain.profile.AdoptionStatus

fun FirestoreAdoptionStatus.toAdoptionStatus(): AdoptionStatus = when(this) {
    FirestoreAdoptionStatus.forAdoption -> AdoptionStatus.FOR_ADOPTION
    FirestoreAdoptionStatus.inProgress -> AdoptionStatus.IN_PROGRESS
    FirestoreAdoptionStatus.adopted -> AdoptionStatus.ADOPTED
}