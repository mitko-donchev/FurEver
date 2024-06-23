package com.epicmillennium.foreverdata.source.mock

import com.epicmillennium.foreverdata.repository.auth.AuthRemoteDataSource
import com.epicmillennium.fureverdomain.auth.Account

class AuthRemoteDataSourceMockImpl : AuthRemoteDataSource {
    private var signedIn = true
    override val userId: String?
        get() = if (signedIn) "mock_user" else null

    override suspend fun isNewAccount(account: Account): Boolean = false

    override fun signOut() {
        signedIn = false
    }

    override suspend fun signInWithGoogle(account: Account) {
        signedIn = true
    }
}