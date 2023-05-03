package com.alina.futureme.presentation.letters.read_letters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material.icons.rounded.Timelapse
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.alina.common.Constants
import com.alina.futureme.presentation.letters.read_letters.liked.LikedLetters
import com.alina.futureme.presentation.letters.read_letters.popular.PopularLetters
import com.alina.futureme.presentation.letters.read_letters.recent.RecentLetter
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReadLetterScreen() {
    val tabs = listOf(
        TabRowItem(
            title = Constants.POPULAR_TAB,
            icon = Icons.Rounded.StarRate,
            screen = { PopularLetters() }
        ),

        TabRowItem(
            title = Constants.RECENT_TAB,
            icon = Icons.Rounded.Timelapse,
            screen = { RecentLetter() }
        ),

        TabRowItem(
            title = Constants.LIKED_TAB,
            icon = Icons.Rounded.Favorite,
            screen = { LikedLetters() }
        )
    )

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ) {
            tabs.forEachIndexed { index, item ->
                Tab(
                    selected = index == pagerState.currentPage,
                    text = { Text(text = item.title) },
                    icon = { Icon(item.icon, "") },
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                )
            }
        }
        HorizontalPager(
            pageCount = tabs.size,
            state = pagerState
        ) {
            tabs[pagerState.currentPage].screen()
        }
    }
}