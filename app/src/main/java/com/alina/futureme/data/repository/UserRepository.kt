package com.alina.futureme.data.repository

import com.alina.futureme.data.data_source.UserRemoteDataSource
import com.alina.futureme.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    auth: FirebaseAuth,
) {

    private val currentUser: String? = auth.currentUser?.email

    suspend fun addUserInFirestore(user: User) =
        userRemoteDataSource.addUserInFirestore(user = user)

    suspend fun updateUserInFirestore(user: User) =
        userRemoteDataSource.updateUserInFirestore(user = user)

    fun addUserLikedLettersInFirestore(likedLetters: String): Result<Any> =
        userRemoteDataSource.addUserLikedLetterInFirestore(currentUser!!, likedLetters)

    fun removeUserLikedLettersInFirestore(likedLetters: String): Result<Any> =
        userRemoteDataSource.removeUserLikedLetterInFirestore(currentUser!!, likedLetters)

    suspend fun deleteUserInFirestore(user: User) =
        userRemoteDataSource.deleteUserInFirestore(user = user)

    suspend fun userExistsInFirestore(email: String): Boolean =
        userRemoteDataSource.userExistsInFirestore(email = email)

    fun observeLikedLettersChanged() =
        userRemoteDataSource.observeLikedLettersChanged(currentUser!!)
}