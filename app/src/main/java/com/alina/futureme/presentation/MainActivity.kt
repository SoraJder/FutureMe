package com.alina.futureme.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.alina.futureme.NotificationWorker
import com.alina.futureme.network.ConnectivityObserver
import com.alina.futureme.network.NetworkConnectivityObserver
import com.alina.futureme.presentation.main.MainScreen
import com.alina.futureme.presentation.no_internet_connection.NoInternetConnection
import com.alina.futureme.presentation.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    private lateinit var connectivityObserver: NetworkConnectivityObserver

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }

        connectivityObserver = NetworkConnectivityObserver(applicationContext)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(
            1, TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext).enqueue(workRequest)

        setContent {
            val startDestinationScreen by splashViewModel.startDestination
            val status by connectivityObserver.observer().collectAsState(
                initial = ConnectivityObserver.Status.Unavailable
            )
            when (status) {
                ConnectivityObserver.Status.Available -> {
                    MainScreen(startDestinationScreen = startDestinationScreen)
                }

                else -> {
                    NoInternetConnection()
                }
            }

        }
    }
}