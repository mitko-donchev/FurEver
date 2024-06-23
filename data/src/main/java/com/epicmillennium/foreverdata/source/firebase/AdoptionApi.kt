package com.epicmillennium.foreverdata.source.firebase

import com.epicmillennium.foreverdata.source.firebase.extension.getTaskResult
import com.epicmillennium.foreverdata.source.firebase.model.FirestoreAdoption
import com.epicmillennium.foreverdata.source.firebase.model.FirestoreAdoptionProperties
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

object AdoptionApi {
    private const val ADOPTIONS = "adoptions"

    suspend fun getAdoptions(): List<FirestoreAdoption> {
        val query = FirebaseFirestore.getInstance().collection(ADOPTIONS)
            .whereArrayContains(FirestoreAdoptionProperties.usersAdoptions, AuthApi.userId!!)
        val result = query.get().getTaskResult()
        return result.toObjects(FirestoreAdoption::class.java)
    }

    suspend fun getAdoption(id: String): FirestoreAdoption {
        return FirebaseFirestore.getInstance()
            .collection(ADOPTIONS)
            .document(id)
            .get()
            .getTaskResult()
            .toObject()!!
    }
}