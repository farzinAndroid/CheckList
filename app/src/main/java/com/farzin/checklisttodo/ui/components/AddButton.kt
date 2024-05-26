package com.farzin.checklisttodo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farzin.checklisttodo.R
import com.farzin.checklisttodo.ui.theme.blueWithoutDarkTheme

@Composable
fun AddButton(
    modifier: Modifier = Modifier,
    backGroundColor: Color = MaterialTheme.colorScheme.blueWithoutDarkTheme,
    text: String = stringResource(R.string.create_task),
    style: TextStyle = MaterialTheme.typography.titleLarge,
    elevation: Dp = 4.dp,
    onClick:()->Unit,
    shapes: CornerBasedShape = Shapes().extraLarge

    ) {


    Surface(
        onClick = { onClick() },
        modifier = modifier,
        shape = shapes,
        color = backGroundColor,
        shadowElevation = elevation,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                color = Color.White,
                style = style,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }

    }


}