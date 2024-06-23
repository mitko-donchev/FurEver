package com.epicmillennium.foreverdata.source.firebase

import com.epicmillennium.foreverdata.source.firebase.extension.getTaskResult
import com.epicmillennium.foreverdata.source.firebase.model.FirestoreAdoptionProperties
import com.epicmillennium.foreverdata.source.firebase.model.FirestoreDog
import com.epicmillennium.foreverdata.source.firebase.model.FirestoreUser
import com.epicmillennium.foreverdata.source.firebase.model.FirestoreUserProperties
import com.epicmillennium.fureverdomain.profile.AdoptionStatus
import com.epicmillennium.fureverdomain.profile.DogProfile
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

object UserApi {

    private const val USERS = "users"
    private const val DOGS = "dogs"
    private const val ADOPTIONS = "adoptions"

    suspend fun createUser(
        userId: String,
        name: String,
        birthdate: Timestamp,
        location: String,
        bio: String,
    ) {
        val user = FirestoreUser(
            name = name,
            birthDate = birthdate,
            location = location,
            bio = bio,
            picture = "",
            liked = emptyList(),
            passed = emptyList()
        )
        FirebaseFirestore.getInstance().collection(USERS).document(userId).set(user).getTaskResult()
    }

    suspend fun getUser(userId: String): FirestoreUser? {
        val snapshot =
            FirebaseFirestore.getInstance().collection(USERS).document(userId).get().getTaskResult()
        return snapshot.toObject<FirestoreUser>()
    }

    suspend fun getCompatibleDogs(currentUser: FirestoreUser): List<FirestoreDog> {
        val excludedDogIds = currentUser.liked + currentUser.passed

        //Build query
        val searchQuery: Query = kotlin.run {
            val query: Query = FirebaseFirestore.getInstance().collection(DOGS)
            //TODO - filter to preset user requirement for example: other dog friendly

            query
        }

        val result = searchQuery.get().getTaskResult()
        //Filter documents
        return result
            .filter { !excludedDogIds.contains(it.id) }
            .mapNotNull { it.toObject<FirestoreDog>() }
    }

    suspend fun swipeDog(swipedDogId: String, isLike: Boolean): String? {
        FirebaseFirestore.getInstance()
            .collection(USERS)
            .document(AuthApi.userId!!)
            .update(
                mapOf(
                    (if (isLike) FirestoreUserProperties.liked else FirestoreUserProperties.passed) to FieldValue.arrayUnion(
                        swipedDogId
                    )
                )
            )
            .getTaskResult()

        FirebaseFirestore.getInstance()
            .collection(USERS)
            .document(AuthApi.userId!!)
            .collection(FirestoreUserProperties.liked)
            .document(swipedDogId)
            .set(mapOf("exists" to true))
            .getTaskResult()

        val hasDogShelterLikedBack = hasDogShelterLikedBack(swipedDogId)
        if (hasDogShelterLikedBack) {
            val matchId = getMatchId(AuthApi.userId!!, swipedDogId)
            FieldValue.serverTimestamp()
            val data = FirestoreAdoptionProperties.toData(swipedDogId, AuthApi.userId!!)
            FirebaseFirestore.getInstance()
                .collection(ADOPTIONS)
                .document(matchId)
                .set(data)
                .getTaskResult()


            return matchId
        }
        return null
    }


    private fun getMatchId(userId1: String, userId2: String): String {
        return if (userId1 > userId2) {
            userId1 + userId2
        } else userId2 + userId1
    }

    private suspend fun hasDogShelterLikedBack(swipedDogId: String): Boolean {
        val result = FirebaseFirestore.getInstance()
            .collection(DOGS)
            .document(swipedDogId)
            .get()
            .await()

        val dog = result.toObject(DogProfile::class.java)
        return dog?.adoptionStatus == AdoptionStatus.IN_PROGRESS
    }

    suspend fun updateUser(
        bio: String,
        location: String
    ) {
        val data = mapOf(
            FirestoreUserProperties.bio to bio,
            FirestoreUserProperties.location to location,
        )
        FirebaseFirestore.getInstance().collection(USERS).document(AuthApi.userId!!).update(data)
            .getTaskResult()
    }


    suspend fun updateUserPictures(picture: String) {
        val data = mapOf(
            FirestoreUserProperties.picture to picture
        )
        FirebaseFirestore.getInstance().collection(USERS).document(AuthApi.userId!!).update(data)
            .getTaskResult()
    }
}