package com.epicmillennium.foreverdata.source.firebase.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

data class FirestoreUser(
    @DocumentId
    var id: String = "",
    val name: String = "",
    val birthDate: Timestamp? = null,
    val location: String = "",
    val bio: String = "",
    val picture: String = "",
    val liked: List<String> = emptyList(),
    val passed: List<String> = emptyList()
)

object FirestoreUserProperties {
    // The property values should reflect the actual name of the data class
    // property they are referencing. This is done so in order to keep track
    // of the property names from a single place.
    const val location = "location"
    const val picture = "picture"
    const val liked = "liked"
    const val passed = "passed"
    const val bio = "bio"
}
