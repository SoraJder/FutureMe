package com.alina.futureme.presentation.onboarding

import androidx.annotation.DrawableRes
import com.alina.futureme.common.Constants
import com.alina.futureme.R

sealed class OnboardPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {

    object FirstPage : OnboardPage(
        image = R.drawable.first_page,
        title = Constants.FIRST_PAGE_TITLE,
        description = Constants.FIRST_PAGE_DESCRIPTION
    )

    object SecondPage : OnboardPage(
        image = R.drawable.second_page,
        title = Constants.SECOND_PAGE_TITLE,
        description = Constants.SECOND_PAGE_DESCRIPTION
    )

    object ThirdPage : OnboardPage(
        image = R.drawable.third_page,
        title = Constants.THIRD_PAGE_TITLE,
        description = Constants.THIRD_PAGE_DESCRIPTION
    )
}