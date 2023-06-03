package com.alina.futureme.presentation.letters.read_letters.popular

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.futureme.common.Resource
import com.alina.futureme.presentation.letters.read_letters.LetterScreenError
import com.alina.futureme.presentation.letters.read_letters.LetterScreenSuccess
import com.alina.futureme.components.ProgressBar

@Composable
fun PopularLetters(
    viewModel: PopularLettersViewModel = hiltViewModel()
) {
    val popularLetterFlow = viewModel.popularLetterFlow.collectAsState()

    popularLetterFlow.value.let { resource ->
        when (resource) {
            is Resource.Failure -> {
               LetterScreenError()
            }

            is Resource.Loading -> ProgressBar()

            is Resource.Success -> {
                LetterScreenSuccess(list = resource.data)
            }
        }
    }
}