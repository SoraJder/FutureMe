package com.alina.futureme.domain.model

import java.time.OffsetDateTime

data class User(
    val email: String,
    val name: String,
    val country: String? = null,
    val birthDate: OffsetDateTime? = null,
    val phoneNumber: String,
    val lettersReceived: List<String> = emptyList(),
)

fun User.asMap(): Map<String, Any?> = mapOf(
    "email" to email,
    "name" to name,
    "country" to country,
    "birthDate" to birthDate,
    "phoneNumber" to phoneNumber,
    "lettersReceived" to lettersReceived,
)
