package com.alina.futureme.presentation.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alina.futureme.common.Resource.Loading
import com.alina.futureme.common.Resource.Success
import com.alina.futureme.data.AuthenticationRepositoryImpl
import com.alina.futureme.domain.repository.AuthenticationRepository
import com.alina.futureme.domain.repository.SignInResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : ViewModel() {

    var signInResponse by mutableStateOf<SignInResponse>(Success(false))
        private set

    fun signInWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        signInResponse = Loading
        signInResponse = repository.signInWithEmail(email, password)
    }
}
