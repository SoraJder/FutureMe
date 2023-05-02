package com.alina.futureme.presentation.letters.read_letters.popular

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.common.Resource
import com.alina.futureme.components.ProgressBar

@Composable
fun PopularLetters(
    viewModel: PopularLettersViewModel = hiltViewModel()
) {
    val popularLetterFlow = viewModel.popularLettersFlow.collectAsState()

    popularLetterFlow.value.let {
        when (it) {
            is Resource.Failure -> {
                PopularLettersScreenError()
            }

            is Resource.Loading -> ProgressBar()

            is Resource.Success -> {
                PopularLettersSuccess(list = it.data)
            }
        }
    }

    /* Column(
         modifier = Modifier.fillMaxSize(),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
     ) {
         Text(text = "Popular")
     }*/
}