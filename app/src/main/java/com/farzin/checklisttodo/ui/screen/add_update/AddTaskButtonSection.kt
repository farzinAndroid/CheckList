package com.farzin.checklisttodo.ui.screen.add_update

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.farzin.checklisttodo.R
import com.farzin.checklisttodo.ui.components.EditTaskButton
import com.farzin.checklisttodo.ui.theme.blueWithoutDarkTheme

@Composable
fun AddTaskButtonSection(onAddClicked:()->Unit) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 20.dp)
            .padding(bottom = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        EditTaskButton(
            text =stringResource(R.string.save),
            onClick = { onAddClicked() },
            color = MaterialTheme.colorScheme.blueWithoutDarkTheme,
            textColor = Color.White,
            modifier = Modifier
                .weight(0.5f)
                .height(40.dp)
        )

    }

}