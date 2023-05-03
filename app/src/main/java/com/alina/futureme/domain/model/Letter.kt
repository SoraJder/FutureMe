package com.alina.futureme.domain.model

data class Letter(
    val sender: String = "",
    val receiver: String = "",
    val dateToArrive: String = "",
    val dateWasSend: String = "",
    val title: String = "",
    val image: String? = null,
    val text: String = "",
    val public: Boolean = false,
    val numberOfLikes: Int = 0,
    val wasReceived: Boolean = false
) {

    var id: String = receiver + "_" + dateWasSend
}

fun Letter.asMap(): Map<String, Any?> = mapOf(
    "id" to id,
    "sender" to sender,
    "receiver" to receiver,
    "dateToArrive" to dateToArrive,
    "dateWasSend" to dateWasSend,
    "title" to title,
    "image" to image,
    "text" to text,
    "public" to public,
    "numberOfLikes" to numberOfLikes,
    "wasReceived" to wasReceived
)

fun Letter.toShowLetter() =
    ShowLetter(
        id = id,
        title = title,
        text = text,
        image = image,
        numberOfLikes = numberOfLikes,
        dateToArrive = dateToArrive,
        dateWasSend = dateWasSend,
        score = 0.0,
        popularScore = 0.0,
        recentScore = 0.0
    )