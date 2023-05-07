package com.alina.futureme.presentation.profile.see_letters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.common.Resource
import com.alina.futureme.components.ProgressBar
import com.alina.futureme.presentation.letters.read_letters.LetterScreenError
import com.alina.futureme.presentation.letters.read_letters.LetterScreenSuccess

@Composable
fun SeeYourLetters(
    viewModel: SeeYourLettersViewModel = hiltViewModel()
) {
    val lettersReceivedFlow = viewModel.letterReceivedFlow.collectAsState()
    lettersReceivedFlow.value.let { resource ->
        when (resource) {
            is Resource.Failure -> {
                LetterScreenError()
            }

            Resource.Loading -> ProgressBar()
            is Resource.Success -> {
                LetterScreenSuccess(list = resource.data)
            }
        }
    }
}