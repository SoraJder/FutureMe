package com.alina.futureme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alina.common.Utils
import com.alina.futureme.domain.model.ShowLetter
import com.alina.futureme.presentation.theme.Typography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LetterCard(
    letterTitle: String,
    letterText: String,
    letterArrivedDate: String,
    letterSendDate: String,
    modifier: Modifier,
    onCardClick: () -> Unit,
    onFavoriteClick: (Boolean) -> Unit
) {
    val isFavorite = remember { mutableStateOf(false) }
    val color: Color =
        if (isFavorite.value) Color.Red else MaterialTheme.colorScheme.onPrimaryContainer
    Card(
        modifier = modifier,
        onClick = onCardClick,
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp,
        backgroundColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            Text(
                text = letterTitle,
                style = Typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = letterText,
                style = Typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.AccessTime,
                    modifier = Modifier.size(16.dp),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )

                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = letterSendDate,
                    style = Typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Spacer(modifier = Modifier.width(2.dp))
                Icon(
                    Icons.Default.ArrowForward,
                    modifier = Modifier.size(12.dp),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = letterArrivedDate,
                    style = Typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.weight(1f)
                )

                IconToggleButton(
                    checked = isFavorite.value,
                    onCheckedChange = {
                        isFavorite.value = !isFavorite.value
                    },
                    modifier = Modifier
                        .size(18.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Icon(
                        tint = color,
                        imageVector = if (isFavorite.value) {
                            Icons.Filled.Favorite
                        } else {
                            Icons.Default.FavoriteBorder
                        },
                        contentDescription = null
                    )
                }

            }
        }
    }
}

@Composable
fun LetterScreenError() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Error. Something went wrong :(")
    }
}

@Composable
fun LetterScreenSuccess(
    list: List<ShowLetter>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            list.forEachIndexed { index, letter ->

                val sendDate = Utils.formmatedDate(letter.dateWasSend.take(10))
                val arrivedDate = Utils.formmatedDate(letter.dateToArrive)

                item {
                    LetterCard(
                        letterTitle = letter.title,
                        letterText = letter.text,
                        letterArrivedDate = arrivedDate,
                        letterSendDate = sendDate,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .wrapContentHeight(),
                        onCardClick = {

                        }
                    ) {

                    }
                }
            }
        }
    }
}