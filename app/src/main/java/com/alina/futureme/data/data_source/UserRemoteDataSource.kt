package com.alina.futureme.data.data_source

import com.alina.futureme.domain.model.User
import com.alina.futureme.domain.model.asMap
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class UserRemoteDataSource @Inject constructor(
    private val usersRef: CollectionReference
) {

    suspend fun addUserInFirestore(user: User) =
        runCatching {
            usersRef
                .document(user.email)
                .set(user.asMap())
                .await()
        }

    fun addUserLikedLetterInFirestore(email: String, likedLetter: String): Result<Any> =
        runCatching {
            usersRef
                .document(email)
                .update("likedLetters", FieldValue.arrayUnion(likedLetter))
        }

    fun removeUserLikedLetterInFirestore(email: String, likedLetter: String): Result<Any> =
        runCatching {
            usersRef
                .document(email)
                .update("likedLetters", FieldValue.arrayRemove(likedLetter))
        }

    suspend fun deleteUserInFirestore(user: String) =
        runCatching {
            usersRef
                .document(user)
                .delete()
                .await()
        }

    suspend fun userExistsInFirestore(email: String): Boolean {
        return try {
            val userDoc = usersRef.document(email).get().await()
            return userDoc?.exists() ?: false
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getLikedLetters(email: String) =
        usersRef
            .document(email)
            .get()
            .await()
            .get("likedLetters") as List<String>?

    suspend fun getReceivedLetters(email: String) =
        usersRef
            .document(email)
            .get()
            .await()
            .get("lettersReceived") as List<String>?

    fun observeLikedLettersChanged(email: String) = callbackFlow {
        val listener = usersRef.document(email).addSnapshotListener { snapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            val list = snapshot?.get("likedLetters") as? List<String> ?: emptyList()
            trySend(list)
        }
        awaitClose {
            listener.remove()
        }
    }
}