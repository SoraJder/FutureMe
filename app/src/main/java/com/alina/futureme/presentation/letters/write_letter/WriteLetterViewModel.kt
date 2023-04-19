package com.alina.futureme.presentation.letters.write_letter

import androidx.lifecycle.ViewModel
import com.alina.futureme.navigation.AppNavigator
import com.alina.futureme.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WriteLetterViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    fun showBottomSheet() {
        appNavigator.tryNavigateTo(Destination.BottomSheetIdeasScreen())
    }

}