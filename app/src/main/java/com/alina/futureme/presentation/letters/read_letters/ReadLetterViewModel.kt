package com.alina.futureme.presentation.letters.read_letters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alina.futureme.data.repository.UserRepository
import com.alina.futureme.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReadLetterViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _likedLetters: MutableStateFlow<List<String>?> = MutableStateFlow(null)
    val likedLetters: StateFlow<List<String>?> = _likedLetters.asStateFlow()

    init {
        viewModelScope.launch {
            _likedLetters.value = userRepository.getUserLikedLetters()
        }
    }

    fun removeLetter(letterId: String) {
        _likedLetters.value?.minus(letterId)
        userRepository.removeUserLikedLettersInFirestore(letterId)
    }

    fun addLetter(letterId: String) {
        _likedLetters.value?.plus(letterId)
        userRepository.addUserLikedLettersInFirestore(letterId)
    }

    fun onNavigateBack() {
        appNavigator.tryNavigateBack()
    }
}