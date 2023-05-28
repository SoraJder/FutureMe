package com.alina.futureme.presentation.letters.send_instant_letter

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.alina.futureme.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SendInstantLetterViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    companion object{
        const val INTENT_TYPE = "message/rfc822"
        const val CHOOSE_TITLE = "Choose an email client: "
    }

    private val _subject: MutableState<String> = mutableStateOf("")
    val subject: State<String> = _subject

    private val _to: MutableState<String> = mutableStateOf("")
    val to: State<String> = _to

    private val _text: MutableState<String> = mutableStateOf("")
    val text: State<String> = _text

    fun updateSubject(subject: String) {
        _subject.value = subject
    }

    fun updateTo(to: String) {
        _to.value = to
    }

    fun updateText(text: String) {
        _text.value = text
    }

    fun sendEmail(context: Context) {
        val intent = Intent(Intent.ACTION_SEND)

        intent.putExtra(Intent.EXTRA_EMAIL, _to.value)
        intent.putExtra(Intent.EXTRA_SUBJECT, _subject.value)
        intent.putExtra(Intent.EXTRA_TEXT, _text.value)

        intent.type = INTENT_TYPE

        context.startActivity(Intent.createChooser(intent, CHOOSE_TITLE))
    }

    fun onNavigateBack() {
        appNavigator.tryNavigateBack()
    }
}