package com.alina.futureme.domain.repository

import com.google.firebase.auth.AuthResult
import io.grpc.internal.SharedResourceHolder.Resource
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
   suspend fun getCurrentUser(): Resource<Boolean>
    suspend fun signInWithEmail(email:String, password:String):Resource<AuthResult>
   suspend  fun signUpWithEmail(email:String, password: String):Resource<AuthResult>

}