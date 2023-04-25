package com.alina.futureme.presentation.letters.read_letters

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material.icons.rounded.Timelapse
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.alina.futureme.common.Constants.LIKED_TAB
import com.alina.futureme.common.Constants.POPULAR_TAB
import com.alina.futureme.common.Constants.RECENT_TAB

sealed class TabRowItem(
    val title:String,
    val icon:ImageVector,
    val screen: @Composable () ->Unit
){
    object PopularLetters : TabRowItem(
        title = POPULAR_TAB,
        icon = Icons.Rounded.StarRate,
        screen = { PopularLetters() }
    )

    object RecentLetters : TabRowItem(
        title = RECENT_TAB,
        icon = Icons.Rounded.Timelapse,
        screen = { RecentLetter()}
    )

    object LikedLetter: TabRowItem(
        title = LIKED_TAB,
        icon = Icons.Rounded.Favorite,
        screen = { LikedLetters()}
    )
}