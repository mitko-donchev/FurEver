package com.epicmillennium.fureverdomain.usecase

import com.epicmillennium.fureverdomain.auth.Account
import com.epicmillennium.fureverdomain.auth.AuthRepository

class SignInUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(account: Account): Result<Unit> =
        Result.runCatching { authRepository.signIn(account) }
}