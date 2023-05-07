package com.alina.futureme.presentation.profile

import androidx.lifecycle.ViewModel
import com.alina.futureme.navigation.AppNavigator
import com.alina.futureme.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    fun onNavigateToSeeYourLetters() {
        appNavigator.tryNavigateTo(Destination.SeeYourLettersScreen())
    }
}