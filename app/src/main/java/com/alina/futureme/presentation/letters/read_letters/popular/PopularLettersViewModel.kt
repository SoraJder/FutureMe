package com.alina.futureme.presentation.letters.read_letters.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alina.futureme.common.Resource
import com.alina.futureme.data.recommendation.Recommendation
import com.alina.futureme.data.repository.LetterRepository
import com.alina.futureme.domain.model.PopularLetter
import com.alina.futureme.domain.model.toPopularLetter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularLettersViewModel @Inject constructor(
    private val letterRepository: LetterRepository
) : ViewModel() {

    private val _popularLettersFlow =
        MutableStateFlow<Resource<List<PopularLetter>>>(Resource.Loading)
    val popularLettersFlow: StateFlow<Resource<List<PopularLetter>>> =
        _popularLettersFlow.asStateFlow()

    fun getPopularLetters() = viewModelScope.launch {
        _popularLettersFlow.value = Resource.Loading

        val popularLetters: List<PopularLetter> = letterRepository.getPublicLetters().mapNotNull {
            it?.toPopularLetter()
        }
        _popularLettersFlow.value = Recommendation.getRecommendations(popularLetters)
    }
}