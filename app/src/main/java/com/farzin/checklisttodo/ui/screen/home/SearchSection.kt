package com.farzin.checklisttodo.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.farzin.checklisttodo.R
import com.farzin.checklisttodo.ui.theme.darkText
import com.farzin.checklisttodo.ui.theme.searchBackground
import com.farzin.checklisttodo.ui.theme.softgray

@Composable
fun SearchSection(
    modifier: Modifier = Modifier,
    onCardClicked:()->Unit
) {



        Card(
            modifier = modifier
                .shadow(4.dp,Shapes().large, spotColor = MaterialTheme.colorScheme.darkText)
                .height(30.dp)
                .clickable { onCardClicked() },
            shape = Shapes().large,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.searchBackground),
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                        .padding(horizontal = 8.dp)
                        .size(18.dp),
                    tint = MaterialTheme.colorScheme.softgray
                )

                Text(
                    text = stringResource(R.string.search),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.softgray
                )

            }
        }
    }