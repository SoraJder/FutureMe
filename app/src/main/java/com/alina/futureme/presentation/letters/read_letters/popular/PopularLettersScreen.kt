package com.alina.futureme.presentation.letters.read_letters.popular

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alina.futureme.domain.model.PopularLetter
import com.alina.futureme.presentation.theme.Typography

@Composable
fun PopularLettersScreenError() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Error. Something went wrong :(")
    }
}

@Composable
fun PopularLettersSuccess(
    list: List<PopularLetter>
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            list.forEachIndexed { index, letter ->
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            }
                            .padding(4.dp)
                            .wrapContentHeight()
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = letter.title,
                                style = Typography.labelLarge
                            )
                            Text(
                                text = letter.text,
                                style = Typography.labelMedium
                            )
                        }
                    }
                }
            }
        }
    }
}