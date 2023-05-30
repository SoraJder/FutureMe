package com.alina.futureme

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.alina.futureme.data.repository.LetterRepository
import com.alina.futureme.data.repository.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.LocalDate

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val letterRepository: LetterRepository,
    private val userRepository: UserRepository,
    private val notificationBuilder: NotificationCompat.Builder,
    private val notificationManager: NotificationManagerCompat
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val letterList = letterRepository.getNotReceivedLetters()
        letterList.forEach { letter ->
            letter?.let {
                val date = LocalDate.parse(it.dateToArrive)
                val today = LocalDate.now()
                if (date == today || today.isAfter(date)) {
                    letterRepository.updateLettersWasReceived(it.id)
                    userRepository.addReceivedLetterInFirestore(it.id)
                    sendNotification()
                }
            }
        }
        return Result.success()
    }

    @SuppressLint("MissingPermission")
    fun sendNotification() {
        notificationManager.notify(1, notificationBuilder.build())
    }
}