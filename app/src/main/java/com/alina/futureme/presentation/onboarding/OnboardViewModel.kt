package com.alina.futureme.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alina.futureme.data.repository.DataStoreRepository
import com.alina.futureme.navigation.AppNavigator
import com.alina.futureme.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor(
    private val repositoryImpl: DataStoreRepository,
    private val appNavigator: AppNavigator
) : ViewModel() {

    fun saveOnboardState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.saveOnBoardingState(completed = completed)
        }
    }

    fun navigateToHomeScreen() {
        appNavigator.tryNavigateTo(Destination.HomeScreen())
    }
}