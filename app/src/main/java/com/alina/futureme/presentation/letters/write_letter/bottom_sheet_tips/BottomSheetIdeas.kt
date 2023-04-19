package com.alina.futureme.presentation.letters.write_letter.bottom_sheet_tips

import androidx.annotation.DrawableRes

sealed class BottomSheetIdeas(
    @DrawableRes
    val image:Int,
    val text: String,
    val explanation:String
){

}
