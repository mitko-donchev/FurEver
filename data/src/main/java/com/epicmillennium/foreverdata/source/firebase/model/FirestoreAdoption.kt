package com.epicmillennium.foreverdata.source.firebase.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FieldValue

data class FirestoreAdoption(
    @DocumentId
    var id: String = "",
    val usersAdoptions: List<String> = emptyList(),
    val timestamp: Timestamp? = null,
    val lastMessage: String? = null
)

object FirestoreAdoptionProperties {
    const val usersAdoptions = "usersAdoptions"
    const val timestamp = "timestamp"
    const val lastMessage = "lastMessage"

    fun toData(user1: String, user2: String): Map<String, Any> = mapOf(
        usersAdoptions to listOf(user1, user2),
        timestamp to FieldValue.serverTimestamp()
    )
}
