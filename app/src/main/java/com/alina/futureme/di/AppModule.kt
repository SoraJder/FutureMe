package com.alina.futureme.di

import android.content.Context
import com.alina.futureme.data.data_source.LetterRemoteDataSource
import com.alina.futureme.data.data_source.UserRemoteDataSource
import com.alina.futureme.data.repository.AuthenticationRepositoryImpl
import com.alina.futureme.data.repository.DataStoreRepository
import com.alina.futureme.data.repository.LetterRepository
import com.alina.futureme.data.repository.UserRepository
import com.alina.futureme.domain.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesAuthRepository(impl: AuthenticationRepositoryImpl): AuthenticationRepository = impl

    @Singleton
    @Provides
    fun providesDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)

    @Provides
    fun providesUserRepository(
        userRemoteDataSourceImpl: UserRemoteDataSource,
        firebaseAuth: FirebaseAuth,
    ): UserRepository =
        UserRepository(userRemoteDataSourceImpl,firebaseAuth)

    @Provides
    fun providesLetterRepository(
        letterRemoteDataSource: LetterRemoteDataSource
    ): LetterRepository = LetterRepository(letterRemoteDataSource)
}