package com.alina.futureme.presentation.letters.write_letter

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.alina.futureme.common.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WriteLetterViewModel @Inject constructor(
) : ViewModel() {

    private val _letterText: MutableState<String> = mutableStateOf("")
    val letterText: State<String> = _letterText

    private val _letterTitle:MutableState<String> = mutableStateOf("A letter from " + Utils.getDate())
    val letterTitle:State<String> = _letterTitle

    private val _mediaFile: MutableState<Uri?> = mutableStateOf(null)
    val mediaFile:State<Uri?> = _mediaFile

    fun updateText(letterText: String) {
        _letterText.value = letterText
    }

    fun updateTitle(letterTitle: String) {
        _letterTitle.value = letterTitle
    }

    fun updateMediaFile(file:Uri?){
        _mediaFile.value=file
    }
}