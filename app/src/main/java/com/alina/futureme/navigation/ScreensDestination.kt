package com.alina.futureme.navigation

import com.alina.common.Constants.FORGOT_PASSWORD_SCREEN
import com.alina.common.Constants.HOME_SCREEN
import com.alina.common.Constants.LOADING_SCREEN
import com.alina.common.Constants.ONBOARD_SCREEN
import com.alina.common.Constants.PROFILE_SCREEN
import com.alina.common.Constants.READ_LETTER_SCREEN
import com.alina.common.Constants.SEE_LETTER_SCREEN
import com.alina.common.Constants.SEE_LETTER_SCREEN_ID
import com.alina.common.Constants.SEE_YOUR_LETTERS_SCREEN
import com.alina.common.Constants.SEND_INSTANT_LETTER_SCREEN
import com.alina.common.Constants.SIGN_IN_SCREEN
import com.alina.common.Constants.SIGN_UP_SCREEN
import com.alina.common.Constants.VERIFY_EMAIL_SCREEN
import com.alina.common.Constants.WRITE_LETTER_SCREEN

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
    object SeeYourLettersScreen :NoArgumentsDestination(SEE_YOUR_LETTERS_SCREEN)
    object LoadingScreen :NoArgumentsDestination(LOADING_SCREEN)
    object  SeeLetterScreen:Destination(SEE_LETTER_SCREEN, SEE_LETTER_SCREEN_ID){
       const val ID= SEE_LETTER_SCREEN_ID

        operator fun invoke(id:String): String = route.appendParams(
            ID to id
        )
    }
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