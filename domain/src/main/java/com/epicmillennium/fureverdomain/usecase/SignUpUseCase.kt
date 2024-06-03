package com.epicmillennium.fureverdomain.usecase

import com.epicmillennium.fureverdomain.auth.Account
import com.epicmillennium.fureverdomain.auth.AuthRepository
import com.epicmillennium.fureverdomain.picture.PictureRepository
import com.epicmillennium.fureverdomain.profile.ProfileRepository
import java.time.LocalDate

class SignUpUseCase(
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository,
    private val pictureRepository: PictureRepository
) {

    suspend operator fun invoke(
        account: Account,
        name: String,
        birthdate: LocalDate,
        location: String,
        bio: String,
        picture: String
    ) {
        authRepository.signUp(account)
        val userId = authRepository.userId
        profileRepository.addUserProfile(userId!!, name, birthdate, location, bio, picture)
        pictureRepository.addPicture(picture)
    }
}