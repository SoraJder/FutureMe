package com.alina.futureme.presentation.home

import androidx.lifecycle.ViewModel
import com.alina.futureme.navigation.AppNavigator
import com.alina.futureme.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    fun onNavigateToProfile() {
        appNavigator.tryNavigateTo(Destination.ProfileScreen())
    }

    fun onNavigateToWriteLetter() {
        appNavigator.tryNavigateTo(Destination.WriteLetterScreen())
    }

    fun onNavigateToReadLetter() {
        appNavigator.tryNavigateTo(Destination.ReadLetterScreen())
    }

    fun onNavigateToSendInstantLetter() {
        appNavigator.tryNavigateTo(Destination.SendInstantLetterScreen())
    }
}