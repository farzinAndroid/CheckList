package com.farzin.checklisttodo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val ColorScheme.mainBlue: Color
    @Composable
    get() =  Color(0xFF1a73e9)

val ColorScheme.mainBackground: Color
    @Composable
    get() =  if (isSystemInDarkTheme()) Color(0xFF001330) else Color(0xFFffffff)

val ColorScheme.darkText: Color
    @Composable
    get() =  if (isSystemInDarkTheme()) Color(0xFFffffff) else Color(0xFF3a3a3a)

val ColorScheme.gray: Color
    @Composable
    get() = Color(0xFF626262)

val ColorScheme.softgray: Color
    @Composable
    get() = Color(0xFFA2A0A0)


val ColorScheme.blueWithoutDarkTheme: Color
    @Composable
    get() = Color(0xFF0384DA)


val ColorScheme.blueWithDarkTheme: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFFFFFFF) else Color(0xFF0384DA)

val ColorScheme.searchBackground: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF000F25) else Color(0xFFffffff)

val ColorScheme.highPriority: Color
    @Composable
    get() =  Color(0xFFD10000)

val ColorScheme.mediumPriority: Color
    @Composable
    get() =  Color(0xFFF1C900)

val ColorScheme.lowPriority: Color
    @Composable
    get() =  Color(0xFF07AC01)