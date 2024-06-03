package com.epicmillennium.foreverdata.repository.auth

import com.epicmillennium.fureverdomain.auth.Account

interface AuthRemoteDataSource {
    val userId: String?
    suspend fun isNewAccount(account: Account): Boolean
    fun signOut()
    suspend fun signInWithGoogle(account: Account)
}