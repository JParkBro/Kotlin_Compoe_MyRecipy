package com.jparkbro.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.jparkbro.core.designsystem.R

val PretendardFamily = FontFamily(
    Font(R.font.pretendard_variable, FontWeight.Light),
    Font(R.font.pretendard_variable, FontWeight.Normal),
    Font(R.font.pretendard_variable, FontWeight.Bold),
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 1.4.em,
        letterSpacing = (-0.25).sp,
    ),
    displayMedium = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.15.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.1.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.5.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.25.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.4.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.1.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.5.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.5.sp,
    ),
)
