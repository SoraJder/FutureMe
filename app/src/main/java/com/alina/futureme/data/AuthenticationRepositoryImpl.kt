package com.alina.futureme.data

import com.alina.futureme.common.Resource
import com.alina.futureme.common.firebase_utils.await
import com.alina.futureme.domain.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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
    ): Resource<FirebaseUser> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun signUpWithEmail(
        email: String,
        password: String
    ): Resource<FirebaseUser> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            Resource.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun sendEmailVerification() : Resource<Boolean> {
        return try {
           auth.currentUser?.sendEmailVerification()?.await()
            Resource.Success(true)
        } catch (e:Exception){
            Resource.Failure(e)
        }
    }

    override suspend fun sendPasswordResetEmail(email: String): Resource<Boolean> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Resource.Success(true)
        } catch (e:Exception){
            Resource.Failure(e)
        }
    }

    override fun signOut() = auth.signOut()
}