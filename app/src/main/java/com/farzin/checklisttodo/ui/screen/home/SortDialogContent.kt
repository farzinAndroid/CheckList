package com.farzin.checklisttodo.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.checklisttodo.R
import com.farzin.checklisttodo.ui.components.MySpacerHeight
import com.farzin.checklisttodo.ui.components.MySpacerWidth
import com.farzin.checklisttodo.ui.theme.blueWithDarkTheme
import com.farzin.checklisttodo.ui.theme.darkText
import com.farzin.checklisttodo.ui.theme.mainBackground
import com.farzin.checklisttodo.ui.theme.softgray
import com.farzin.checklisttodo.viewModel.DataStoreViewModel
import com.farzin.checklisttodo.viewModel.TaskViewModel


@Composable
fun SortDialogContent(
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    taskViewModel: TaskViewModel = hiltViewModel(),
) {


    val options = listOf(
        stringResource(R.string.high),
        stringResource(R.string.low),
        stringResource(R.string.none),
    )


    var selectedOptions by when (dataStoreViewModel.getSort()) {
        1 -> {
            remember { mutableStateOf(options[0]) }
        }

        2 -> {
            remember { mutableStateOf(options[1]) }
        }

        3 -> {
            remember { mutableStateOf(options[2]) }
        }

        else -> {
            remember { mutableStateOf(options[2]) }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.25f)
            .clip(Shapes().large)
            .background(MaterialTheme.colorScheme.mainBackground),
        horizontalAlignment = Alignment.Start
    ) {


        MySpacerHeight(height = 10.dp)

        Text(
            text = stringResource(R.string.list_based_on_priority),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        MySpacerHeight(height = 6.dp)

        options.forEachIndexed { index, title ->

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {


                RadioButton(
                    selected = (options[index] == selectedOptions),
                    onClick = {
                        selectedOptions = title

                        dataStoreViewModel.saveSort(index + 1)

                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.blueWithDarkTheme,
                        unselectedColor = MaterialTheme.colorScheme.softgray,
                        disabledUnselectedColor = MaterialTheme.colorScheme.softgray
                    )
                )

                MySpacerWidth(width = 8.dp)

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.darkText,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}