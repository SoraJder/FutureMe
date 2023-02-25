package com.alina.futureme.common

import android.content.Context
import android.widget.Toast

object Utils {

    fun showMessage(
        context: Context,
        message: String?
    ) = Toast.makeText(context, message, Toast.LENGTH_LONG).show()

    fun isEmailAndPasswordVaild(email: String, password: String): Boolean {
        val emailRegex =
            """^([a-zA-Z]+)([!#$%&'*+\-/=?^_{|]?[a-zA-Z0-9]+)*@([a-zA-Z]+)([.-]?[a-zA-Z0-9]+)*\.[a-zA-Z]{2,4}""".toRegex()
        val passwordRegex =
            """^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@${'$'}%^&*-]).{8,}""".toRegex()
        return passwordRegex.matches(password) && emailRegex.matches(email)
    }

}