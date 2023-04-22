package com.alina.futureme.presentation.letters.write_letter

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alina.futureme.common.Utils
import com.alina.futureme.common.Utils.isEmailValid
import com.alina.futureme.data.repository.LetterRepository
import com.alina.futureme.domain.model.Letter
import com.alina.futureme.navigation.AppNavigator
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class WriteLetterViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val letterRepository: LetterRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _letterText: MutableState<String> = mutableStateOf("")
    val letterText: State<String> = _letterText

    private val _letterTitle: MutableState<String> =
        mutableStateOf("A letter from " + Utils.getDate())
    val letterTitle: State<String> = _letterTitle

    private val _mediaFile: MutableState<Uri?> = mutableStateOf(null)
    val mediaFile: State<Uri?> = _mediaFile

    private val _email: MutableState<String> = mutableStateOf("")
    val email: State<String> = _email

    private val _isPublic: MutableState<Boolean> = mutableStateOf(false)
    val isPublic: State<Boolean> = _isPublic

    private val _selectedDate: MutableState<LocalDate?> = mutableStateOf(null)
    val selectedDate: State<LocalDate?> = _selectedDate

    fun updateText(letterText: String) {
        _letterText.value = letterText
    }

    fun updateTitle(letterTitle: String) {
        _letterTitle.value = letterTitle
    }

    fun updateMediaFile(file: Uri?) {
        _mediaFile.value = file
    }

    fun updateEmail(email: String) {
        _email.value = email
    }

    fun updateIsPublic(isPublic: Boolean) {
        _isPublic.value = isPublic
    }

    fun updateSelectedDate(selectedDate: LocalDate) {
        _selectedDate.value = selectedDate
    }

    fun checkFields(): Boolean {
        return if (_letterText.value.isBlank() || _letterTitle.value.isBlank() ||
            _email.value.isBlank() || _selectedDate.value == null
        ) {
            false
        } else {
            if (isEmailValid(_email.value)) return true
            false
        }
    }

    fun sendLetter() = viewModelScope.launch {
        letterRepository.addLetterInFirestore(
            Letter(
                sender = firebaseAuth.currentUser?.email ?: "",
                receiver = _email.value,
                dateToArrive = _selectedDate.value!!,
                dateWasSend = LocalDateTime.now(),
                title = _letterTitle.value,
                text = _letterText.value,
                image = _mediaFile.value,
                public = _isPublic.value
            )
        )
        clearFields()
    }

    private fun clearFields() {
        _email.value = ""
        _selectedDate.value = null
        _letterTitle.value = ""
        _letterText.value = ""
        _mediaFile.value = null
        _isPublic.value = false
    }

    fun onNavigateBack() {
        appNavigator.tryNavigateBack()
    }
}