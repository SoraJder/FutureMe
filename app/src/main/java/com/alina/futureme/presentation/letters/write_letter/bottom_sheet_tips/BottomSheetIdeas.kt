package com.alina.futureme.presentation.letters.write_letter.bottom_sheet_tips

import androidx.annotation.DrawableRes
import com.alina.futureme.R
import com.alina.common.Constants

sealed class BottomSheetIdeasPage(
    @DrawableRes
    val image: Int,
    val textToShow: String,
    val explanation: String = Constants.BOTTOM_SHEET_EXPLANATION,
    val letterTemplate: String
) {

    object FirstIdea : BottomSheetIdeasPage(
        image = R.drawable.change,
        textToShow = Constants.FUTURE_ADVICE_TITLE,
        letterTemplate = Constants.ADVICE_TEXT
    )

    object SecondIdea : BottomSheetIdeasPage(
        image = R.drawable.people,
        textToShow = Constants.FAVORITE_TITLE,
        letterTemplate = Constants.FAVORITE_TEXT
    )

    object ThirdIdea : BottomSheetIdeasPage(
        image = R.drawable.fireworks,
        textToShow = Constants.PREDICTION_TITLE,
        letterTemplate = Constants.PREDICTION_TEXT
    )

    object FourthIdea : BottomSheetIdeasPage(
        image = R.drawable.success,
        textToShow = Constants.ACHIEVE_TITLE,
        letterTemplate = Constants.ACHIEVE_TEXT
    )

    object FifthIdea : BottomSheetIdeasPage(
        image = R.drawable.praying,
        textToShow = Constants.GRATEFUL_TITLE,
        letterTemplate = Constants.GRATITUDE_TEXT
    )
}
