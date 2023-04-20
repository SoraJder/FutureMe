package com.alina.futureme.presentation.letters.write_letter.bottom_sheet_tips

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alina.futureme.presentation.letters.write_letter.WriteLetterViewModel
import com.alina.futureme.presentation.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomSheetIdeasScreen(
    onSelected: () -> Unit
) {

    val pages = listOf(
        BottomSheetIdeasPage.FirstIdea,
        BottomSheetIdeasPage.SecondIdea,
        BottomSheetIdeasPage.ThirdIdea,
        BottomSheetIdeasPage.FourthIdea,
        BottomSheetIdeasPage.FifthIdea
    )

    val pagerState = rememberPagerState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(325.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ideas for your letter",
                style = Typography.titleLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalPager(
                modifier = Modifier.weight(10f),
                pageCount = 5,
                state = pagerState,
                verticalAlignment = Alignment.CenterVertically
            ) { position ->
                BottomSheetPagerScreen(page = pages[position], onSelected = onSelected)
            }

            Row(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                repeat(5) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray

                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(10.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun BottomSheetPagerScreen(
    page: BottomSheetIdeasPage,
    onSelected: () -> Unit,
    viewModel: WriteLetterViewModel = hiltViewModel()
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                onSelected()
                viewModel.updateText(page.letterTemplate)
            },
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = page.image),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
            )

            Text(
                text = page.textToShow,
                style = Typography.bodyMedium,
                textAlign = TextAlign.Center,
            )

            Text(
                text = page.explanation,
                style = Typography.labelSmall,
                textAlign = TextAlign.Center,
            )
        }
    }
}
