package com.epicmillennium.foreverdata.source.firebase

import com.epicmillennium.foreverdata.repository.auth.AuthRemoteDataSource
import com.epicmillennium.fureverdomain.auth.Account

class AuthRemoteDataSourceImpl : AuthRemoteDataSource {
    override val userId: String?
        get() = AuthApi.userId

    override suspend fun isNewAccount(account: Account): Boolean =
        AuthApi.isNewAccount(account.email)

    override fun signOut() {
        AuthApi.signOut()
    }

    override suspend fun signInWithGoogle(account: Account) {
        AuthApi.signInWithGoogle(account.idToken)
    }
}