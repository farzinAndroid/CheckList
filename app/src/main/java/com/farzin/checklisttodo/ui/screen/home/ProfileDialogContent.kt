package com.farzin.checklisttodo.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.farzin.checklisttodo.ui.theme.mainBackground

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileDialogContent(
    profileList: List<Int>,
    onProfileClicked: (Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.3f)
            .clip(Shapes().large)
            .background(MaterialTheme.colorScheme.mainBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        FlowRow(
            maxItemsInEachRow = 3,
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly
        ) {

            for (profile in profileList) {
                Image(
                    painter = painterResource(profile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(96.dp)
                        .padding(12.dp)
                        .clip(Shapes().small)
                        .clickable {
                            onProfileClicked(profile)
                        },
                    contentScale = ContentScale.FillBounds

                )
            }

        }

    }

}