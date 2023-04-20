package com.alina.futureme.navigation

import com.alina.futureme.common.Constants.FORGOT_PASSWORD_SCREEN
import com.alina.futureme.common.Constants.HOME_SCREEN
import com.alina.futureme.common.Constants.ONBOARD_SCREEN
import com.alina.futureme.common.Constants.PROFILE_SCREEN
import com.alina.futureme.common.Constants.READ_LETTER_SCREEN
import com.alina.futureme.common.Constants.SEND_INSTANT_LETTER_SCREEN
import com.alina.futureme.common.Constants.SIGN_IN_SCREEN
import com.alina.futureme.common.Constants.SIGN_UP_SCREEN
import com.alina.futureme.common.Constants.VERIFY_EMAIL_SCREEN
import com.alina.futureme.common.Constants.WRITE_LETTER_SCREEN

sealed class Destination(protected val route: String, vararg params: String) {

    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    sealed class NoArgumentsDestination(route: String) : Destination(route) {

        operator fun invoke(): String = route
    }

    object HomeScreen : NoArgumentsDestination(HOME_SCREEN)

    object SignInScreen : NoArgumentsDestination(SIGN_IN_SCREEN)
    object ForgotPasswordScreen : NoArgumentsDestination(FORGOT_PASSWORD_SCREEN)
    object SignUpScreen : NoArgumentsDestination(SIGN_UP_SCREEN)
    object VerifyEmailScreen : NoArgumentsDestination(VERIFY_EMAIL_SCREEN)
    object OnboardScreen : NoArgumentsDestination(ONBOARD_SCREEN)
    object ProfileScreen : NoArgumentsDestination(PROFILE_SCREEN)
    object WriteLetterScreen : NoArgumentsDestination(WRITE_LETTER_SCREEN)
    object ReadLetterScreen : NoArgumentsDestination(READ_LETTER_SCREEN)
    object SendInstantLetterScreen : NoArgumentsDestination(SEND_INSTANT_LETTER_SCREEN)
}

internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}