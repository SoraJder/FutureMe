package com.alina.futureme.presentation.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alina.futureme.common.Resource
import com.alina.futureme.domain.repository.AuthenticationRepository
import com.alina.futureme.navigation.AppNavigator
import com.alina.futureme.navigation.Destination
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val repository: AuthenticationRepository,
    private val appNavigator: AppNavigator
) : ViewModel() {

    private val _signInFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signInFlow: StateFlow<Resource<FirebaseUser>?> = _signInFlow

    private val _googleSignInFlow = MutableStateFlow<Resource<AuthResult>?>(null)
    val googleSignInFlow:StateFlow<Resource<AuthResult>?> = _googleSignInFlow

    private val _signUpFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signUpFlow: StateFlow<Resource<FirebaseUser>?> = _signUpFlow

    private val _forgotPasswordFlow = MutableStateFlow<Resource<Boolean>>(Resource.Success(false))
    val forgotPasswordFlow: StateFlow<Resource<Boolean>> = _forgotPasswordFlow

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    fun signIn(email: String, password: String) = viewModelScope.launch {
        _signInFlow.value = Resource.Loading
        val result = repository.signInWithEmail(email, password)
        _signInFlow.value = result
    }

    fun googleSignIn(credentials: AuthCredential) = viewModelScope.launch {
        _googleSignInFlow.value=Resource.Loading
        val result = repository.googleSignIn(credentials)
        _googleSignInFlow.value = result
    }
    fun signUp(email: String, password: String) = viewModelScope.launch {
        _signUpFlow.value = Resource.Loading
        val result = repository.signUpWithEmail(email, password)
        _signUpFlow.value = result
    }

    fun forgotPassword(email: String) = viewModelScope.launch {
        _forgotPasswordFlow.value = Resource.Loading
        val result = repository.sendPasswordResetEmail(email)
        _forgotPasswordFlow.value = result
    }

    fun sendEmailVerification() = viewModelScope.launch {
        repository.sendEmailVerification()
    }

    fun isEmailVerified() = currentUser?.isEmailVerified

    fun signOut() {
        repository.signOut()
        _signInFlow.value = null
        _signUpFlow.value = null
    }

    fun onNavigateToSignUpButtonClicked() {
        appNavigator.tryNavigateTo(Destination.SignUpScreen())
    }

    fun onNavigateToSignInButtonClicked() {
        appNavigator.tryNavigateBack(Destination.SignInScreen())
    }

    //TODO cand dai back din verifyEmail sa se duca direct la signIn
    fun onNavigateToVerifyEmail() {
        appNavigator.tryNavigateTo(Destination.VerifyEmailScreen())
    }

    fun onNavigateToForgotPasswordButtonClicked() {
        appNavigator.tryNavigateTo(Destination.ForgotPasswordScreen())
    }

    fun onNavigateToHomeButtonClicked() {
        appNavigator.tryNavigateTo(Destination.HomeScreen())
    }
}