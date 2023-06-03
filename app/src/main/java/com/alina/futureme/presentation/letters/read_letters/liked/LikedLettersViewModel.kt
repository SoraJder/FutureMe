package com.alina.futureme.presentation.letters.read_letters.liked

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alina.futureme.common.Resource
import com.alina.futureme.data.recommendation.Recommendation
import com.alina.futureme.data.repository.LetterRepository
import com.alina.futureme.data.repository.UserRepository
import com.alina.futureme.domain.model.ShowLetter
import com.alina.futureme.domain.model.toShowLetter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikedLettersViewModel @Inject constructor(
    private val letterRepository: LetterRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _likedLettersFlow =
        MutableStateFlow<Resource<List<ShowLetter>>>(Resource.Loading)
    val likedLettersFlow: StateFlow<Resource<List<ShowLetter>>> =
        _likedLettersFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _likedLettersFlow.value = Resource.Loading

            val likedLetters = userRepository.getLikedLetters()
            val showLetters: MutableList<ShowLetter> = mutableListOf()

            likedLetters?.forEach { id ->
                val showLetter = letterRepository.getLetterById(id)
                showLetter?.let { letter ->
                    showLetters.add(letter.toShowLetter())
                }
            }
            _likedLettersFlow.value = Recommendation.getRecommendations(showLetters)
        }
    }

    fun initialization() = viewModelScope.launch {
        viewModelScope.launch {
            _likedLettersFlow.value = Resource.Loading

            val likedLetters = userRepository.getLikedLetters()
            val showLetters: MutableList<ShowLetter> = mutableListOf()

            likedLetters?.forEach { id ->
                val showLetter = letterRepository.getLetterById(id)
                showLetter?.let { letter ->
                    showLetters.add(letter.toShowLetter())
                }
            }
            _likedLettersFlow.value = Recommendation.getRecommendations(showLetters)
        }
    }
}