package com.farzin.checklisttodo.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.farzin.checklisttodo.R


@Composable
fun FilterButton(
    modifier: Modifier = Modifier,
    onFilterClicked:()->Unit
) {



    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){

        Box(
            modifier = Modifier
                .clip(Shapes().extraLarge)
                .background(Color.Black)
                .size(30.dp),
            contentAlignment = Alignment.Center
        ){

            IconButton(onClick = { onFilterClicked() }) {
                Icon(
                    painter = painterResource(R.drawable.sort),
                    contentDescription ="",
                    modifier = Modifier
                        .padding(6.dp),
                    tint = Color.White
                )
            }

        }


    }


}