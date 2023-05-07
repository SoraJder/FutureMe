package com.alina.futureme.presentation.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alina.common.Resource
import com.alina.futureme.data.repository.LetterRepository
import com.alina.futureme.data.repository.UserRepository
import com.alina.futureme.domain.model.User
import com.alina.futureme.domain.repository.AuthenticationRepository
import com.alina.futureme.navigation.AppNavigator
import com.alina.futureme.navigation.Destination
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val userRepository: UserRepository,
    private val letterRepository: LetterRepository,
    private val storageReference: StorageReference,
    private val appNavigator: AppNavigator
) : ViewModel() {

    private val _signInFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signInFlow: StateFlow<Resource<FirebaseUser>?> = _signInFlow

    private val _googleSignInFlow = MutableStateFlow<Resource<AuthResult>?>(null)
    val googleSignInFlow: StateFlow<Resource<AuthResult>?> = _googleSignInFlow

    private val _signUpFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signUpFlow: StateFlow<Resource<FirebaseUser>?> = _signUpFlow

    private val _forgotPasswordFlow = MutableStateFlow<Resource<Boolean>>(Resource.Success(false))
    val forgotPasswordFlow: StateFlow<Resource<Boolean>> = _forgotPasswordFlow

    private val _userExistFlow = MutableStateFlow<Boolean?>(null)
    val userExistFlow: StateFlow<Boolean?> = _userExistFlow

    val currentUser: FirebaseUser?
        get() = authenticationRepository.currentUser

    //FIREBASE FUNCTION PROVIDED
    fun signIn(email: String, password: String) = viewModelScope.launch {
        _signInFlow.value = Resource.Loading
        val result = authenticationRepository.signInWithEmail(email, password)
        _signInFlow.value = result
    }

    fun googleSignIn(credentials: AuthCredential) = viewModelScope.launch {
        _googleSignInFlow.value = Resource.Loading
        val result = authenticationRepository.googleSignIn(credentials)
        _googleSignInFlow.value = result
    }

    fun signUp(email: String, password: String, name: String) = viewModelScope.launch {
        _signUpFlow.value = Resource.Loading
        val result = authenticationRepository.signUpWithEmail(email, password, name)
        _signUpFlow.value = result
    }

    fun forgotPassword(email: String) = viewModelScope.launch {
        _forgotPasswordFlow.value = Resource.Loading
        val result = authenticationRepository.sendPasswordResetEmail(email)
        _forgotPasswordFlow.value = result
    }

    fun sendEmailVerification() = viewModelScope.launch {
        authenticationRepository.sendEmailVerification()
        signOut()
    }

    fun isEmailVerified() = currentUser?.isEmailVerified

    fun userExistsInFirestore(email: String) = viewModelScope.launch {
        _userExistFlow.value = userRepository.userExistsInFirestore(email)
    }

    fun removeUser() = viewModelScope.launch {
        letterRepository.getImageUriFromLetter(currentUser?.email!!).forEach { uri ->
            uri?.let {
                storageReference.child(it).delete()
            }
        }

        letterRepository.deleteLettersWithSpecificReceiver(currentUser?.email!!)
        userRepository.deleteUserInFirestore(currentUser?.email!!)
        currentUser?.delete()?.await()
    }

    fun signOut() {
        authenticationRepository.signOut()
        _signInFlow.value = null
        _signUpFlow.value = null
    }

    //CREATE USER IN FIRESTORE
    fun createUser(name: String) {
        viewModelScope.launch {
            userRepository.addUserInFirestore(
                User(
                    email = currentUser?.email ?: "",
                    name = name,
                    country = "",
                    birthDate = "",
                    phoneNumber = "",
                    lettersReceived = emptyList()
                )
            )
        }
    }

    //NAVIGATION PART
    fun onNavigateToSignUpButtonClicked() {
        appNavigator.tryNavigateTo(Destination.SignUpScreen())
    }

    fun onNavigateToSignInButtonClicked() {
        appNavigator.tryNavigateBack(Destination.SignInScreen())
    }

    fun onNavigateSignOutButtonClicked() {
        appNavigator.tryNavigateBack()
        appNavigator.tryNavigateTo(Destination.SignInScreen())
    }

    //TODO cand dai back din verifyEmail sa se duca direct la signIn
    fun onNavigateToVerifyEmail() {
        appNavigator.tryNavigateTo(Destination.VerifyEmailScreen())
    }

    fun onNavigateToForgotPasswordButtonClicked() {
        appNavigator.tryNavigateTo(Destination.ForgotPasswordScreen())
    }

    fun onNavigateToOnboardScreen() {
        appNavigator.tryNavigateBack()
        appNavigator.tryNavigateTo(Destination.OnboardScreen())
    }
}