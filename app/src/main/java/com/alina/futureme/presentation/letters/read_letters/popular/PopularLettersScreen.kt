package com.alina.futureme.presentation.letters.read_letters.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alina.common.Utils
import com.alina.futureme.domain.model.PopularLetter
import com.alina.futureme.presentation.theme.Typography

@Composable
fun PopularLettersScreenError() {
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
fun PopularLettersSuccess(
    list: List<PopularLetter>
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
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            }
                            .padding(4.dp)
                            .wrapContentHeight(),
                        shape = MaterialTheme.shapes.large,
                        elevation = 8.dp,
                        backgroundColor =MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Column(
                            modifier = Modifier.padding(4.dp)
                        ) {

                            Text(
                                text = letter.title,
                                style = Typography.titleMedium
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = letter.text,
                                style = Typography.bodyMedium
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
                                    contentDescription = null
                                )

                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    text = "$sendDate",
                                    style = Typography.labelMedium
                                )

                                Spacer(modifier = Modifier.width(2.dp))
                                Icon(
                                    Icons.Default.ArrowForward,
                                    modifier = Modifier.size(12.dp),
                                    contentDescription = null
                                )

                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    text = "$arrivedDate",
                                    style = Typography.labelMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}