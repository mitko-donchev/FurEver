package com.epicmillennium.fureverdomain.usecase

import com.epicmillennium.fureverdomain.auth.AuthRepository

class IsUserSignedInUseCase(private val authRepository: AuthRepository) {

    operator fun invoke(): Boolean = authRepository.userId != null
}