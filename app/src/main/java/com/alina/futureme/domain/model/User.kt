package com.alina.futureme.domain.model

data class User(
    val email: String,
    val name: String,
    val country: String,
    val birthDate: String,
    val phoneNumber: String,
    val lettersReceived: List<String> = emptyList(),
    val likedLetters: List<String> = emptyList()
)

fun User.asMap(): Map<String, Any?> = mapOf(
    "email" to email,
    "name" to name,
    "country" to country,
    "birthDate" to birthDate,
    "phoneNumber" to phoneNumber,
    "lettersReceived" to lettersReceived,
    "likedLetters" to likedLetters
)