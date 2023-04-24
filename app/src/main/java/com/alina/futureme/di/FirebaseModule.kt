package com.alina.futureme.di

import com.alina.futureme.data.data_source.LetterRemoteDataSource
import com.alina.futureme.data.data_source.UserRemoteDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    companion object {

        const val LETTERS_PATH: String = "letters"
        const val USERS_PATH: String = "users"
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun providesFirebaseStorage(): StorageReference = Firebase.storage.reference

    @Provides
    fun providesUserRemoteDataSource(firestore: FirebaseFirestore): UserRemoteDataSource =
        UserRemoteDataSource(
            firestore.collection(USERS_PATH)
        )

    @Provides
    fun providesLetterRemoteDataSource(firestore: FirebaseFirestore): LetterRemoteDataSource =
        LetterRemoteDataSource(
            firestore.collection(LETTERS_PATH)
        )

}