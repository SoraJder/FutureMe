package com.alina.futureme.presentation.see_letter

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alina.futureme.data.repository.LetterRepository
import com.alina.futureme.domain.model.Letter
import com.alina.futureme.navigation.AppNavigator
import com.alina.futureme.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeeLetterViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val letterRepository: LetterRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _showLetter: MutableState<Letter> = mutableStateOf(Letter())
    val showLetter = _showLetter

    init {
        viewModelScope.launch {
            val id = savedStateHandle.get<String>(Destination.SeeLetterScreen.ID)!!
            _showLetter.value = letterRepository.getLetterById(id)!!
        }
    }

    fun onNavigateBack() {
        appNavigator.tryNavigateTo(Destination.ProfileScreen())
    }
}