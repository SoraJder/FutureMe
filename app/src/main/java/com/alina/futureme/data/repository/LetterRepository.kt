package com.alina.futureme.data.repository

import com.alina.futureme.data.data_source.LetterRemoteDataSource
import com.alina.futureme.domain.model.Letter
import javax.inject.Inject

class LetterRepository @Inject constructor(
    private val letterRemoteDataSource: LetterRemoteDataSource,
) {

    suspend fun addLetterInFirestore(letter: Letter) =
        letterRemoteDataSource.addLetterInFirestore(letter = letter)

    suspend fun getPublicLetters() = letterRemoteDataSource.getPublicLetters()

    fun updateNumberOfLikes(letterId: String, value: Int) =
        letterRemoteDataSource.updateNumberOfLikes(letterId, value)

    suspend fun getLetterById(id: String) =
        letterRemoteDataSource.getLetterById(id)

    suspend fun deleteLettersWithSpecificReceiver(receiver: String) =
        letterRemoteDataSource.deleteLettersWithSpecificReveiver(receiver)
}