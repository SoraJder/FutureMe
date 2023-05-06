package com.alina.futureme.presentation.profile

import androidx.lifecycle.ViewModel
import com.alina.futureme.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

}