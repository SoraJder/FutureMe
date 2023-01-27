package com.alina.futureme.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.alina.futureme.R

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.work_sans_light)),
        fontSize = 96.sp
    ),
    h2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.work_sans_semi_bold)),
        fontSize = 60.sp
    ),
    h3 = TextStyle(
        fontFamily = FontFamily(Font(R.font.work_sans_regular)),
        fontSize = 48.sp
    ),
    h4 = TextStyle(
        fontFamily = FontFamily(Font(R.font.work_sans_regular)),
        fontSize = 34.sp
    ),
    h5 = TextStyle(
        fontFamily = FontFamily(Font(R.font.work_sans_bold)),
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = FontFamily(Font(R.font.work_sans_medium)),
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.work_sans_regular)),
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.work_sans_medium)),
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.work_sans_regular)),
        fontSize = 18.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.work_sans_regular)),
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily(Font(R.font.work_sans_medium)),
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily(Font(R.font.work_sans_regular)),
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = FontFamily(Font(R.font.work_sans_semi_bold)),
        fontSize = 12.sp,
    )
)