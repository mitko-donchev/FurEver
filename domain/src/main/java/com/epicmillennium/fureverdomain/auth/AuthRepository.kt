package com.epicmillennium.fureverdomain.auth

interface AuthRepository {
    val userId: String?
    suspend fun signIn(account: Account)
    suspend fun signUp(account: Account)
    fun signOut()
}