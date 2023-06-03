package com.alina.futureme.presentation.letters.read_letters.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alina.futureme.common.Resource
import com.alina.futureme.data.recommendation.Recommendation
import com.alina.futureme.data.repository.LetterRepository
import com.alina.futureme.domain.model.ShowLetter
import com.alina.futureme.domain.model.toShowLetter
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

    private val _popularLetterFlow =
        MutableStateFlow<Resource<List<ShowLetter>>>(Resource.Loading)
    val popularLetterFlow: StateFlow<Resource<List<ShowLetter>>> =
        _popularLetterFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _popularLetterFlow.value = Resource.Loading

            val showLetters: List<ShowLetter> =
                letterRepository.getPublicLetters().mapNotNull {
                    it?.toShowLetter()
                }
            _popularLetterFlow.value = Recommendation.getRecommendations(showLetters)
        }
    }
}