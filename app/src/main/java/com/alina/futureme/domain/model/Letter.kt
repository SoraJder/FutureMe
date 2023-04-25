package com.alina.futureme.domain.model

import android.net.Uri
import java.time.LocalDate
import java.time.LocalDateTime

data class Letter(
    val sender: String,
    val receiver: String,
    val dateToArrive: LocalDate,
    val dateWasSend: LocalDateTime,
    val title: String,
    val image: Uri?,
    val text: String,
    val public: Boolean,
    val numberOfLikes:Int = 0,
) {

    val id: String = receiver + "_" + dateWasSend.toString()
}

fun Letter.asMap(): Map<String, Any?> = mapOf(
    "id" to id,
    "sender" to sender,
    "receiver" to receiver,
    "dateToArrive" to dateToArrive.toString(),
    "dateWasSend" to dateWasSend.toString(),
    "title" to title,
    "image" to image?.toString(),
    "text" to text,
    "public" to public,
    "numberOfLikes" to numberOfLikes
)