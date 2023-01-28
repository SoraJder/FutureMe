package com.alina.futureme.domain.repository

import com.alina.futureme.common.Resource
import com.google.firebase.auth.FirebaseUser

interface AuthenticationRepository {

    val currentUser: FirebaseUser?
    suspend fun signInWithEmail(email: String, password: String): Resource<Boolean>
    suspend fun signUpWithEmail(email: String, password: String): Resource<Boolean>
    fun signOut()

}