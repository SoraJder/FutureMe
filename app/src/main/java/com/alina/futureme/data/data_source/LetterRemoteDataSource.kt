package com.alina.futureme.data.data_source

import com.alina.futureme.domain.model.Letter
import com.alina.futureme.domain.model.asMap
import com.google.firebase.firestore.CollectionReference
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

}