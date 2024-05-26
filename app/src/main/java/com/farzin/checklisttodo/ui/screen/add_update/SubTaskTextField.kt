package com.farzin.checklisttodo.ui.screen.add_update

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farzin.checklisttodo.ui.theme.darkText
import com.farzin.checklisttodo.ui.theme.searchBackground

@Composable
fun SubTaskTextField(
    textValue: String,
    onValueChanged: (String) -> Unit,
    isCompleted: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        BasicTextField(
            value = textValue,
            onValueChange =  onValueChanged ,
            textStyle = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.darkText,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            ),
            modifier = Modifier
                .weight(0.8f)
                .padding(vertical = 6.dp),
            singleLine = true,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.darkText)
        ) { innerTextField ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .shadow(4.dp, Shapes().large, spotColor = MaterialTheme.colorScheme.darkText),
                shape = Shapes().large,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.searchBackground),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    innerTextField()
                }
            }

        }


        Checkbox(
            checked = isCompleted,
            onCheckedChange = {
                onCheckedChange(isCompleted)
            },
            modifier = Modifier
                .weight(0.2f)
        )

    }
}