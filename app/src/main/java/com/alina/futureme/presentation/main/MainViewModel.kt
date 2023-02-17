package com.alina.futureme.presentation.main

import androidx.lifecycle.ViewModel
import com.alina.futureme.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    appNavigator: AppNavigator
): ViewModel() {

    val navigationChannel = appNavigator.navigationChannel
}