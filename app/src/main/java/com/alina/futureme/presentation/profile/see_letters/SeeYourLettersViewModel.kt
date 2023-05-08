package com.alina.futureme.presentation.profile.see_letters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alina.common.Resource
import com.alina.futureme.data.recommendation.Recommendation
import com.alina.futureme.data.repository.LetterRepository
import com.alina.futureme.data.repository.UserRepository
import com.alina.futureme.domain.model.ShowLetter
import com.alina.futureme.domain.model.toShowLetter
import com.alina.futureme.navigation.AppNavigator
import com.alina.futureme.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeeYourLettersViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val letterRepository: LetterRepository,
    private val appNavigator: AppNavigator
) : ViewModel() {

    private val _lettersReceivedFlow =
        MutableStateFlow<Resource<List<ShowLetter>>>(Resource.Loading)
    val letterReceivedFlow = _lettersReceivedFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _lettersReceivedFlow.value = Resource.Loading

            val receivedLetters = userRepository.getReceivedLetters()
            val showLetters: MutableList<ShowLetter> = mutableListOf()

            receivedLetters?.forEach { id ->
                val showLetter = letterRepository.getLetterById(id)
                showLetter?.let { letter ->
                    showLetters.add(letter.toShowLetter())
                }
            }
            _lettersReceivedFlow.value = Recommendation.getRecent(showLetters)
        }
    }

    fun onNavigateBack() {
        appNavigator.tryNavigateTo(Destination.ProfileScreen())
    }
}