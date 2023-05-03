package com.alina.futureme.presentation.letters.read_letters.recent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alina.common.Resource
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
class RecentLettersViewModel @Inject constructor(
    private val letterRepository: LetterRepository
) : ViewModel() {

    private val _recentLetterFlow =
        MutableStateFlow<Resource<List<ShowLetter>>>(Resource.Loading)
    val recentLetterFlow: StateFlow<Resource<List<ShowLetter>>> =
        _recentLetterFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _recentLetterFlow.value = Resource.Loading

            val showLetters: List<ShowLetter> =
                letterRepository.getPublicLetters().mapNotNull {
                    it?.toShowLetter()
                }
            _recentLetterFlow.value = Recommendation.getRecent(showLetters)
        }
    }
}