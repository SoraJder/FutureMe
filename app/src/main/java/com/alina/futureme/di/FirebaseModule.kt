package com.alina.futureme.di

import com.alina.futureme.data.AuthenticationRepositoryImpl
import com.alina.futureme.domain.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth():FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun providesAuthRepository(impl: AuthenticationRepositoryImpl): AuthenticationRepository = impl
}