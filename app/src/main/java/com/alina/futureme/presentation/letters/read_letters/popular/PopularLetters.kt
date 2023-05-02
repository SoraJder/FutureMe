package com.alina.futureme.presentation.letters.read_letters.popular

import android.util.Log
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

    popularLetterFlow.value.let { resource ->
        when (resource) {
            is Resource.Failure -> {
                PopularLettersScreenError()
            }

            is Resource.Loading -> ProgressBar()

            is Resource.Success -> {
                PopularLettersSuccess(list = resource.data)
                resource.data.forEach {
                    Log.d("RECOMANDARE", "[${it.score}, ${it.title}, ${it.text}]")
                }
            }
        }
    }
}