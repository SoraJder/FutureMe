package com.alina.futureme.presentation.letters.send_instant_letter

import com.alina.futureme.common.Constants.BIRTHDAY_SUBJECT
import com.alina.futureme.common.Constants.BIRTHDAY_TEXT
import com.alina.futureme.common.Constants.LOVER_SUBJECT
import com.alina.futureme.common.Constants.LOVER_TEXT
import com.alina.futureme.common.Constants.PARENTS_SUBJECT
import com.alina.futureme.common.Constants.PARENTS_TEXT

sealed class TemplateEmail(
    val subject: String,
    val text: String
) {

    object Birthday : TemplateEmail(
        subject = BIRTHDAY_SUBJECT,
        text = BIRTHDAY_TEXT
    )

    object Parents : TemplateEmail(
        subject = PARENTS_SUBJECT,
        text = PARENTS_TEXT
    )

    object Lover : TemplateEmail(
        subject = LOVER_SUBJECT,
        text = LOVER_TEXT
    )
}
