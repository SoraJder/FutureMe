package com.alina.futureme.data.data_source

import com.alina.futureme.domain.model.Letter
import com.alina.futureme.domain.model.asMap
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LetterRemoteDataSource @Inject constructor(
    private val lettersRef: CollectionReference
) {

    suspend fun addLetterInFirestore(letter: Letter) =
        runCatching {
            lettersRef
                .document(letter.id)
                .set(letter.asMap())
                .await()
        }

    suspend fun getPublicLetters() =
        lettersRef
            .whereEqualTo("wasReceived", true)
            .whereEqualTo("public", true)
            .get()
            .await()
            .documents
            .map { document ->
                document.toObject(Letter::class.java)
            }

    suspend fun getNotReceivedLetters() =
        lettersRef
            .whereEqualTo("wasReceived", false)
            .get()
            .await()
            .documents
            .map { document ->
                document.toObject(Letter::class.java)
            }

    suspend fun getImageUriFromLetter(receiver: String) =
        lettersRef
            .whereEqualTo("receiver", receiver)
            .get()
            .await()
            .documents
            .map { documentSnapshot ->
                documentSnapshot.toObject(Letter::class.java)?.imageUri
            }

    suspend fun deleteLettersWithSpecificReveiver(receiver: String) =
        runCatching {
            lettersRef
                .whereEqualTo("receiver", receiver)
                .get()
                .await()
                .forEach { queryDocumentSnapshot ->
                    queryDocumentSnapshot.reference.delete()
                }
        }

    fun updateNumberOfLikes(letterId: String, value: Int) =
        runCatching {
            lettersRef
                .document(letterId)
                .update("numberOfLikes", FieldValue.increment(value.toLong()))
        }

    fun updateLettersWasReceived(letterId: String) =
        runCatching {
            lettersRef
                .document(letterId)
                .update("wasReceived", true)
        }

    suspend fun getLetterById(id: String): Letter? =
        lettersRef
            .document(id)
            .get()
            .await()
            .toObject(Letter::class.java)
}