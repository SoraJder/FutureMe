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

    suspend fun getNotReceivedLetters() = letterRemoteDataSource.getNotReceivedLetters()
    fun updateNumberOfLikes(letterId: String, value: Int) =
        letterRemoteDataSource.updateNumberOfLikes(letterId, value)

    fun updateLettersWasReceived(letterId: String) =
        letterRemoteDataSource.updateLettersWasReceived(letterId)

    suspend fun getLetterById(id: String) =
        letterRemoteDataSource.getLetterById(id)

    suspend fun getImageUriFromLetter(receiver: String) =
        letterRemoteDataSource.getImageUriFromLetter(receiver)

    suspend fun deleteLettersWithSpecificReceiver(receiver: String) =
        letterRemoteDataSource.deleteLettersWithSpecificReveiver(receiver)
}