package com.alina.futureme.di

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC
import androidx.core.app.NotificationManagerCompat
import com.alina.futureme.R
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

    @Singleton
    @Provides
    fun providesNotificationBuilder(
        @ApplicationContext context: Context
    ): NotificationCompat.Builder{
        return NotificationCompat.Builder(context,"Letters Channel ID")
            .setContentTitle("Your letter arrived")
            .setContentText("Open your letter and see your message from the past")
            .setSmallIcon(R.drawable.ic_futureme)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(VISIBILITY_PUBLIC)
    }

    @Singleton
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManagerCompat {
        val notificationManager = NotificationManagerCompat.from(context)
        val channel = NotificationChannel(
            "Letters Channel ID",
            "Letters Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
        return notificationManager
    }
}