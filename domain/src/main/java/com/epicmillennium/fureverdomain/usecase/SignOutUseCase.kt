package com.epicmillennium.fureverdomain.usecase

import com.epicmillennium.fureverdomain.auth.AuthRepository

class SignOutUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(): Result<Unit> =
        Result.runCatching { authRepository.signOut() }
}