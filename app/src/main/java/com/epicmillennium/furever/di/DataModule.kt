package com.epicmillennium.furever.di

import android.content.Context
import com.epicmillennium.foreverdata.repository.adoption.AdoptionRemoteDataSource
import com.epicmillennium.foreverdata.repository.adoption.AdoptionRepositoryImpl
import com.epicmillennium.foreverdata.repository.auth.AuthRemoteDataSource
import com.epicmillennium.foreverdata.repository.auth.AuthRepositoryImpl
import com.epicmillennium.foreverdata.repository.message.MessageRemoteDataSource
import com.epicmillennium.foreverdata.repository.message.MessageRepositoryImpl
import com.epicmillennium.foreverdata.repository.picture.PictureRemoteDataSource
import com.epicmillennium.foreverdata.repository.picture.PictureRepositoryImpl
import com.epicmillennium.foreverdata.repository.profile.ProfileRemoteDataSource
import com.epicmillennium.foreverdata.repository.profile.ProfileRepositoryImpl
import com.epicmillennium.foreverdata.source.firebase.AdoptionRemoteDataSourceImpl
import com.epicmillennium.foreverdata.source.firebase.AuthRemoteDataSourceImpl
import com.epicmillennium.foreverdata.source.firebase.MessageRemoteDataSourceImpl
import com.epicmillennium.foreverdata.source.firebase.PictureRemoteDataSourceImpl
import com.epicmillennium.foreverdata.source.firebase.ProfileRemoteDataSourceImpl
import com.epicmillennium.foreverdata.source.mock.AdoptionRemoteDataSourceMockImpl
import com.epicmillennium.foreverdata.source.mock.AuthRemoteDataSourceMockImpl
import com.epicmillennium.foreverdata.source.mock.MessageRemoteDataSourceMockImpl
import com.epicmillennium.foreverdata.source.mock.PictureRemoteDataSourceMockImpl
import com.epicmillennium.foreverdata.source.mock.ProfileRemoteDataSourceMockImpl
import com.epicmillennium.furever.BuildConfig
import com.epicmillennium.fureverdomain.adoption.AdoptionRepository
import com.epicmillennium.fureverdomain.auth.AuthRepository
import com.epicmillennium.fureverdomain.message.MessageRepository
import com.epicmillennium.fureverdomain.picture.PictureRepository
import com.epicmillennium.fureverdomain.profile.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NormalSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockSource

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun providePictureRemoteDataSource(
        @NormalSource normalSource: PictureRemoteDataSourceImpl,
        @MockSource mockSource: PictureRemoteDataSourceMockImpl
    ) = if (BuildConfig.BUILD_TYPE == "mock") mockSource else normalSource

    @Provides
    @Singleton
    fun provideProfileRemoteDataSource(
        @NormalSource normalSource: ProfileRemoteDataSourceImpl,
        @MockSource mockSource: ProfileRemoteDataSourceMockImpl
    ) = if (BuildConfig.BUILD_TYPE == "mock") mockSource else normalSource

    @Provides
    @Singleton
    fun provideMessageRemoteDataSource(
        @NormalSource normalSource: MessageRemoteDataSourceImpl,
        @MockSource mockSource: MessageRemoteDataSourceMockImpl
    ) = if (BuildConfig.BUILD_TYPE == "mock") mockSource else normalSource

    @Provides
    @Singleton
    fun provideAdoptionRemoteDataSource(
        @NormalSource normalSource: AdoptionRemoteDataSourceImpl,
        @MockSource mockSource: AdoptionRemoteDataSourceMockImpl
    ) = if (BuildConfig.BUILD_TYPE == "mock") mockSource else normalSource

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(
        @NormalSource normalSource: AuthRemoteDataSourceImpl,
        @MockSource mockSource: AuthRemoteDataSourceMockImpl
    ) = if (BuildConfig.BUILD_TYPE == "mock") mockSource else normalSource
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideProfileRepository(profileRemoteDataSource: ProfileRemoteDataSource): ProfileRepository =
        ProfileRepositoryImpl(profileRemoteDataSource)

    @Provides
    @Singleton
    fun providePictureRepository(pictureRemoteDataSource: PictureRemoteDataSource): PictureRepository =
        PictureRepositoryImpl(pictureRemoteDataSource)

    @Provides
    @Singleton
    fun provideMessageRepository(messageRemoteDataSource: MessageRemoteDataSource): MessageRepository =
        MessageRepositoryImpl(messageRemoteDataSource)

    @Provides
    @Singleton
    fun provideAdoptionRepository(matchRemoteDataSource: AdoptionRemoteDataSource): AdoptionRepository =
        AdoptionRepositoryImpl(matchRemoteDataSource)

    @Provides
    @Singleton
    fun provideAuthRepository(authRemoteDataSource: AuthRemoteDataSource): AuthRepository =
        AuthRepositoryImpl(authRemoteDataSource)
}

@Module
@InstallIn(SingletonComponent::class)
object SourceModule {
    @NormalSource
    @Provides
    @Singleton
    fun providePictureRemoteDataSource() = PictureRemoteDataSourceImpl()

    @NormalSource
    @Provides
    @Singleton
    fun provideProfileRemoteDataSource() = ProfileRemoteDataSourceImpl()

    @NormalSource
    @Provides
    @Singleton
    fun provideMessageRemoteDataSource() = MessageRemoteDataSourceImpl()

    @NormalSource
    @Provides
    @Singleton
    fun provideAdoptionRemoteDataSource() = AdoptionRemoteDataSourceImpl()

    @NormalSource
    @Provides
    @Singleton
    fun provideAuthRemoteDataSource() = AuthRemoteDataSourceImpl()
}

@Module
@InstallIn(SingletonComponent::class)
object MockSourceModule {
    @MockSource
    @Provides
    @Singleton
    fun providePictureRemoteDataSource(@ApplicationContext appContext: Context) =
        PictureRemoteDataSourceMockImpl(appContext)

    @MockSource
    @Provides
    @Singleton
    fun provideProfileRemoteDataSource() = ProfileRemoteDataSourceMockImpl()

    @MockSource
    @Provides
    @Singleton
    fun provideMessageRemoteDataSource() = MessageRemoteDataSourceMockImpl()

    @MockSource
    @Provides
    @Singleton
    fun provideAdoptionRemoteDataSource() = AdoptionRemoteDataSourceMockImpl()

    @MockSource
    @Provides
    @Singleton
    fun provideAuthRemoteDataSource() = AuthRemoteDataSourceMockImpl()
}