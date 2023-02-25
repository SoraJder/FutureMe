package com.alina.futureme.presentation.sign_up

import androidx.lifecycle.ViewModel
import com.alina.futureme.domain.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : ViewModel()  {

}
