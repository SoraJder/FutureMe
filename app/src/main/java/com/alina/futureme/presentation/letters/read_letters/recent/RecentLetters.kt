package com.alina.futureme.presentation.letters.read_letters.recent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.futureme.common.Resource
import com.alina.futureme.components.ProgressBar
import com.alina.futureme.presentation.letters.read_letters.LetterScreenError
import com.alina.futureme.presentation.letters.read_letters.LetterScreenSuccess

@Composable
fun RecentLetter(
    viewModel: RecentLettersViewModel = hiltViewModel()
) {
    val recentLetterFlow = viewModel.recentLetterFlow.collectAsState()

    recentLetterFlow.value.let { resource ->
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