package com.alina.futureme.domain.repository

import com.alina.futureme.common.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
interface AuthenticationRepository {

    val currentUser: FirebaseUser?
    suspend fun signInWithEmail(email: String, password: String): Resource<FirebaseUser>?

    suspend fun googleSignIn(credential: AuthCredential): Resource<AuthResult>?
    suspend fun signUpWithEmail(email: String, password: String): Resource<FirebaseUser>?

    suspend fun sendEmailVerification() : Resource<Boolean>

    suspend fun sendPasswordResetEmail(email: String) : Resource<Boolean>
    fun signOut()
}