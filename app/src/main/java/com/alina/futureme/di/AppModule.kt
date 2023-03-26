package com.alina.futureme.di

import android.content.Context
import com.alina.futureme.data.data_source.UserRemoteDataSource
import com.alina.futureme.data.repository.AuthenticationRepositoryImpl
import com.alina.futureme.data.repository.DataStoreRepository
import com.alina.futureme.data.repository.UserRepository
import com.alina.futureme.domain.repository.AuthenticationRepository
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
    ): UserRepository =
        UserRepository(userRemoteDataSourceImpl)
}