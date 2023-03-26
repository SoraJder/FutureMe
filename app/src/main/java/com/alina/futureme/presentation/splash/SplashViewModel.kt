package com.alina.futureme.presentation.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alina.futureme.data.AuthenticationRepositoryImpl
import com.alina.futureme.data.DataStoreRepositoryImpl
import com.alina.futureme.navigation.Destination
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO o pagina de loading
class SplashViewModel @Inject constructor(
    private val dataStoreRepositoryImpl: DataStoreRepositoryImpl,
    private val authenticationRepositoryImpl: AuthenticationRepositoryImpl
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<Destination> =
        mutableStateOf(Destination.SignInScreen)
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
            _isLoading.value = false
        }
    }
}