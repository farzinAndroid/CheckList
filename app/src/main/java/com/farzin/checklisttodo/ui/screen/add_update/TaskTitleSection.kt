package com.farzin.checklisttodo.ui.screen.add_update

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farzin.checklisttodo.ui.components.MySpacerHeight
import com.farzin.checklisttodo.ui.theme.darkText

@Composable
fun TaskTitleSection(
    title:String,
    textValue:String,
    onTextValueChanged:(String)->Unit,
    icon: Painter? = null,
    onClick:()->Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 38.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        
        MySpacerHeight(height = 8.dp)

        AddUpdateTextField(textValue, onTextValueChanged,icon,onClick)

    }




}