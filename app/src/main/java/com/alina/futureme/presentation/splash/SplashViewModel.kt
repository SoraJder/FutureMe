package com.alina.futureme.presentation.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alina.futureme.data.repository.AuthenticationRepositoryImpl
import com.alina.futureme.data.repository.DataStoreRepository
import com.alina.futureme.navigation.Destination
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val dataStoreRepositoryImpl: DataStoreRepository,
    private val authenticationRepositoryImpl: AuthenticationRepositoryImpl
) : ViewModel() {

    val isLoading: MutableState<Boolean> = mutableStateOf(true)

    private val _startDestination: MutableState<Destination> =
        mutableStateOf(Destination.LoadingScreen)
    val startDestination = _startDestination

    init {
        viewModelScope.launch {
            dataStoreRepositoryImpl.readOnBoardingState().collect { completed ->
                authenticationRepositoryImpl.currentUser?.let {
                    if (completed) {
                        _startDestination.value = Destination.HomeScreen
                    } else {
                        _startDestination.value = Destination.OnboardScreen
                    }
                } ?: run {
                    _startDestination.value = Destination.SignInScreen
                }
            }
        }
    }
}