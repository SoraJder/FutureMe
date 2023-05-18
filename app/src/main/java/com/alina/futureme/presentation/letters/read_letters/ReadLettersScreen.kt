package com.alina.futureme.presentation.letters.read_letters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material.icons.rounded.Timelapse
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.common.Constants
import com.alina.futureme.R
import com.alina.futureme.presentation.letters.read_letters.liked.LikedLetters
import com.alina.futureme.presentation.letters.read_letters.popular.PopularLetters
import com.alina.futureme.presentation.letters.read_letters.recent.RecentLetter
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ReadLetterScreen(
    viewModel: ReadLetterViewModel = hiltViewModel()
) {
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

    val pagerState = rememberPagerState { tabs.size }
    val coroutineScope = rememberCoroutineScope()

    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehaviour,
                title = {
                    Text(
                        text = stringResource(R.string.home_screen),
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.onNavigateBack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            Column {
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
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
                    state = pagerState
                ) {
                    tabs[pagerState.currentPage].screen()
                }
            }
        }
    }
}