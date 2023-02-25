package com.alina.futureme.data

import com.alina.futureme.common.Resource
import com.alina.futureme.domain.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationRepositoryImpl @Inject constructor(
   private val auth: FirebaseAuth
) : AuthenticationRepository {

    override val currentUser: FirebaseUser?
        get() = auth.currentUser

    override suspend fun signInWithEmail(
        email: String,
        password: String
    ): Resource<Boolean> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun signUpWithEmail(
        email: String,
        password: String
    ): Resource<Boolean> {
        return try {
            auth.createUserWithEmailAndPassword(email, password)
            Resource.Success(true)
        } catch (e: java.lang.Exception) {
            Resource.Failure(e)
        }
    }

    override fun signOut() = auth.signOut()
}