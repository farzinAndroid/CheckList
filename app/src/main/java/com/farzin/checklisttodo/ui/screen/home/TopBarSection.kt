package com.farzin.checklisttodo.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farzin.checklisttodo.R
import com.farzin.checklisttodo.ui.components.MySpacerHeight
import com.farzin.checklisttodo.ui.theme.darkText

@Composable
fun TopBarSection(
    onSearchClicked:()->Unit,
    onImageClicked:()->Unit,
    onFilterClicked:()->Unit,
    profile:Int
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Image(
            painter = painterResource(profile),
            contentDescription ="",
            modifier = Modifier
                .padding(16.dp)
                .size(100.dp)
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colorScheme.darkText, CircleShape)
                .clickable { onImageClicked() },
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = stringResource(R.string.my_tasks),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.darkText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            MySpacerHeight(height = 8.dp)


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                SearchSection(
                    modifier = Modifier.weight(0.7f)
                ){
                    onSearchClicked()
                }

                FilterButton(
                    modifier = Modifier
                        .height(30.dp)
                        .weight(0.3f),
                    onFilterClicked = {
                        onFilterClicked()
                    }
                )

            }


            MySpacerHeight(height = 16.dp)






        }

    }

}