package com.alina.futureme.data.repository

import com.alina.futureme.data.data_source.LetterRemoteDataSource
import com.alina.futureme.domain.model.Letter
import javax.inject.Inject

class LetterRepository @Inject constructor(
    private val letterRemoteDataSource: LetterRemoteDataSource
) {

    suspend fun addLetterInFirestore(letter: Letter) =
        letterRemoteDataSource.addLetterInFirestore(letter = letter)
}