package com.alina.futureme.domain.repository

import com.alina.futureme.common.Resource
import com.google.firebase.auth.FirebaseUser

typealias SignInResponse = Resource<Boolean>
typealias SignUpResponse = Resource<Boolean>
interface AuthenticationRepository {

    val currentUser: FirebaseUser?
    suspend fun signInWithEmail(email: String, password: String): SignInResponse
    suspend fun signUpWithEmail(email: String, password: String): SignUpResponse
    fun signOut()
}