package com.alina.futureme.presentation.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alina.futureme.common.Resource
import com.alina.futureme.domain.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : ViewModel() {

    private val _signInFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signInFlow: StateFlow<Resource<FirebaseUser>?> = _signInFlow

    private val _signUpFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signUpFlow: StateFlow<Resource<FirebaseUser>?> = _signUpFlow

    private val _forgotPasswordFlow = MutableStateFlow<Resource<Boolean>> (Resource.Success(false))
    val forgotPasswordFlow: StateFlow<Resource<Boolean>>  = _forgotPasswordFlow

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    init {
        if (repository.currentUser != null) {
            _signInFlow.value = Resource.Success(repository.currentUser!!)
        }
    }

    fun signIn(email: String, password: String) = viewModelScope.launch {
        _signInFlow.value = Resource.Loading
        val result = repository.signInWithEmail(email, password)
        _signInFlow.value = result
    }

    fun signUp(email: String, password: String) = viewModelScope.launch {
        _signUpFlow.value = Resource.Loading
        val result = repository.signUpWithEmail(email, password)
        _signUpFlow.value = result
    }

    fun forgotPassword(email:String) = viewModelScope.launch {
        _forgotPasswordFlow.value = Resource.Loading
       val result = repository.sendPasswordResetEmail(email)
        _forgotPasswordFlow.value = result
    }

    fun signOut() {
        repository.signOut()
        _signInFlow.value = null
        _signUpFlow.value = null
    }
}