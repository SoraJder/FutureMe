package com.alina.futureme.presentation.letters.write_letter

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WriteLetterViewModel @Inject constructor(
) : ViewModel() {

    private val _letterText: MutableStateFlow<String> = MutableStateFlow("")
    val letterText: StateFlow<String> = _letterText.asStateFlow()

    fun updateText(letterText: String) {
        _letterText.value = letterText
    }
}