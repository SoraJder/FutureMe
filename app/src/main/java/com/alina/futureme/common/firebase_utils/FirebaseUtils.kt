@file:OptIn(ExperimentalCoroutinesApi::class)

package com.alina.futureme.common.firebase_utils

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

//Se evita folosirea callback-urilor, returneaza direct daca a dat fail sau nu
suspend fun <T> Task<T>.await(): T {
    return suspendCancellableCoroutine { cancellableContinuation ->
        addOnCompleteListener { task ->
            task.exception?.let {
                cancellableContinuation.resumeWithException(it)
            } ?: run {
                cancellableContinuation.resume(task.result, null)
            }
        }
    }
}