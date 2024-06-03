package com.epicmillennium.furever.di

import com.epicmillennium.fureverdomain.adoption.AdoptionRepository
import com.epicmillennium.fureverdomain.auth.AuthRepository
import com.epicmillennium.fureverdomain.message.MessageRepository
import com.epicmillennium.fureverdomain.picture.PictureRepository
import com.epicmillennium.fureverdomain.profile.ProfileRepository
import com.epicmillennium.fureverdomain.usecase.GetAdoptionsUseCase
import com.epicmillennium.fureverdomain.usecase.GetDogProfilesUseCase
import com.epicmillennium.fureverdomain.usecase.GetMaxBirthdateUseCase
import com.epicmillennium.fureverdomain.usecase.GetMessagesUseCase
import com.epicmillennium.fureverdomain.usecase.GetPictureUseCase
import com.epicmillennium.fureverdomain.usecase.GetUserProfileUseCase
import com.epicmillennium.fureverdomain.usecase.IsUserSignedInUseCase
import com.epicmillennium.fureverdomain.usecase.LikeDogUseCase
import com.epicmillennium.fureverdomain.usecase.SendMessageUseCase
import com.epicmillennium.fureverdomain.usecase.SignInUseCase
import com.epicmillennium.fureverdomain.usecase.SignOutUseCase
import com.epicmillennium.fureverdomain.usecase.SignUpUseCase
import com.epicmillennium.fureverdomain.usecase.SkippedDogUseCase
import com.epicmillennium.fureverdomain.usecase.UpdatePictureUseCase
import com.epicmillennium.fureverdomain.usecase.UpdateProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideSignUpUseCase(
        authRepository: AuthRepository,
        profileRepository: ProfileRepository,
        pictureRepository: PictureRepository
    ) = SignUpUseCase(authRepository, profileRepository, pictureRepository)

    @Provides
    fun provideSignInUseCase(authRepository: AuthRepository) = SignInUseCase(authRepository)

    @Provides
    fun provideSignOutUseCase(authRepository: AuthRepository) = SignOutUseCase(authRepository)

    @Provides
    fun provideIsUserSignedInUseCase(authRepository: AuthRepository) =
        IsUserSignedInUseCase(authRepository)

    @Provides
    fun provideGetProfileUseCase(profileRepository: ProfileRepository) =
        GetUserProfileUseCase(profileRepository)

    @Provides
    fun provideLikeProfileUseCase(
        profileRepository: ProfileRepository,
        adoptionRepository: AdoptionRepository
    ) = LikeDogUseCase(profileRepository, adoptionRepository)

    @Provides
    fun providePassProfileUseCase(profileRepository: ProfileRepository) =
        SkippedDogUseCase(profileRepository)

    @Provides
    fun provideSendMessageUseCase(messageRepository: MessageRepository) =
        SendMessageUseCase(messageRepository)

    @Provides
    fun provideGetMessagesUseCase(messageRepository: MessageRepository) =
        GetMessagesUseCase(messageRepository)

    @Provides
    fun provideGetMaxBirthdateUseCase() = GetMaxBirthdateUseCase()

    @Provides
    fun provideGetAdoptionsUseCase(adoptionRepository: AdoptionRepository) =
        GetAdoptionsUseCase(adoptionRepository)

    @Provides
    fun provideUpdateProfileUseCase(profileRepository: ProfileRepository) =
        UpdateProfileUseCase(profileRepository)

    @Provides
    fun provideGetProfilesUseCase(profileRepository: ProfileRepository) =
        GetDogProfilesUseCase(profileRepository)

    @Provides
    fun provideGetPictureUseCase(pictureRepository: PictureRepository) =
        GetPictureUseCase(pictureRepository)

    @Provides
    fun provideUpdatePicturesUseCase(
        profileRepository: ProfileRepository,
        pictureRepository: PictureRepository
    ) = UpdatePictureUseCase(profileRepository, pictureRepository)
}