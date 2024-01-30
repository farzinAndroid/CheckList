package com.farzin.checklisttodo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.farzin.checklisttodo.R

val samim = FontFamily( Font(R.font.samim))
val samim_bold = FontFamily( Font(R.font.amim_bold))

val Typography.extraBoldNumber : TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = samim_bold,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
    )


val Typography.extraSmall : TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = samim,
        fontSize = 11.sp,
        lineHeight = 25.sp
    )

val Typography.veryExtraSmall : TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = samim,
        fontSize = 10.sp,
    )


val Typography = Typography(
    bodyLarge = TextStyle(//body1
        fontFamily = samim,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 25.sp
    ),
    bodyMedium = TextStyle(//body2
        fontFamily = samim,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        lineHeight = 25.sp
    ),

    displayLarge = TextStyle(//h1
        fontFamily = samim_bold,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 25.sp
    ),
    displayMedium = TextStyle(//h2
        fontFamily = samim_bold,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 25.sp
    ),
    displaySmall = TextStyle(//h3
        fontFamily = samim_bold,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 25.sp
    ),
    headlineMedium = TextStyle(//h4
        fontFamily = samim,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 25.sp
    ),
    headlineSmall = TextStyle(//h5
        fontFamily = samim,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 25.sp
    ),
    titleLarge = TextStyle(//h5
        fontFamily = samim,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 25.sp
    ),
    titleMedium = TextStyle(
        fontFamily = samim,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 25.sp
    )

)