package com.farzin.checklisttodo.ui.screen.add_update

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farzin.checklisttodo.R
import com.farzin.checklisttodo.model.add_update.PriorityModel
import com.farzin.checklisttodo.ui.components.MySpacerHeight
import com.farzin.checklisttodo.ui.theme.darkText
import com.farzin.checklisttodo.ui.theme.highPriority
import com.farzin.checklisttodo.ui.theme.lowPriority
import com.farzin.checklisttodo.ui.theme.mediumPriority

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PrioritySection(priorityNumber: Int = -1, priorityCallback: (Int) -> Unit) {


    val context = LocalContext.current

    val priorityList = listOf(

        PriorityModel(
            text = stringResource(R.string.high),
            color = MaterialTheme.colorScheme.highPriority,
            number = 1
        ),

        PriorityModel(
            text = stringResource(R.string.meduim),
            color = MaterialTheme.colorScheme.mediumPriority,
            number = 2
        ),

        PriorityModel(
            text = stringResource(R.string.low),
            color = MaterialTheme.colorScheme.lowPriority,
            number = 3
        ),
    )


    var selectedPriority by remember { mutableStateOf<PriorityModel?>(null) }
    if (priorityNumber != -1) {
        selectedPriority = priorityList.find { priorityNumber == it.number }
    }

    MySpacerHeight(height = 10.dp)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 38.dp),
        horizontalAlignment = Alignment.Start
    ) {


        Text(
            text = stringResource(R.string.set_priority),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )


        MySpacerHeight(height = 8.dp)

        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            maxItemsInEachRow = 3
        ) {

            priorityList.forEach {priority->
                PriorityItem(
                    priority = priority,
                    onClick = {
                        selectedPriority = priority

                        when(selectedPriority?.text.toString()){
                            context.getString(R.string.low)->{
                                priorityCallback(3)
                            }
                            context.getString(R.string.meduim)->{
                                priorityCallback(2)
                            }
                            context.getString(R.string.high)->{
                                priorityCallback(1)
                            }
                        }


                    },
                    isSelected = priority == selectedPriority
                )
            }


        }


    }

}