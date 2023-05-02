package com.alina.futureme.data.repository

import com.alina.futureme.data.data_source.UserRemoteDataSource
import com.alina.futureme.domain.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {

    suspend fun addUserInFirestore(user: User) =
        userRemoteDataSource.addUserInFirestore(user = user)

    suspend fun updateUserInFirestore(user: User) =
        userRemoteDataSource.updateUserInFirestore(user = user)

    suspend fun deleteUserInFirestore(user: User) =
        userRemoteDataSource.deleteUserInFirestore(user = user)

    suspend fun userExistsInFirestore(email: String): Boolean =
        userRemoteDataSource.userExistsInFirestore(email = email)
}