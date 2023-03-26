package com.alina.futureme.data.data_source

import com.alina.futureme.domain.model.User
import com.alina.futureme.domain.model.asMap
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val usersRef: CollectionReference
) {

    suspend fun addUserInFirestore(user: User) =
        runCatching {
            usersRef
                .document(user.email)
                .set(user)
                .await()
        }

    suspend fun updateUserInFirestore(user: User) =
        runCatching {
            usersRef
                .document(user.email)
                .update(user.asMap())
                .await()
        }

    suspend fun deleteUserInFirestore(user: User) =
        runCatching {
            usersRef
                .document(user.email)
                .delete()
                .await()
        }

    //TODO posibil sa fie nevoie si de o functie get User by email
    //Emailul e identificator unic
}