package com.jparkbro.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.jparkbro.core.designsystem.R

val DongleFontFamily = FontFamily(
    Font(R.font.dongle_light, FontWeight.Light),
    Font(R.font.dongle_regular, FontWeight.Normal),
    Font(R.font.dongle_bold, FontWeight.Bold),
)

val GaeGuFontFamily = FontFamily(
    Font(R.font.gaegu_light, FontWeight.Light),
    Font(R.font.gaegu_regular, FontWeight.Normal),
    Font(R.font.gaegu_bold, FontWeight.Bold),
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = DongleFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 1.4.em,
        letterSpacing = (-0.25).sp,
    ),
    displayMedium = TextStyle(
        fontFamily = DongleFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = DongleFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = DongleFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = DongleFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = DongleFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = DongleFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = DongleFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.15.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = DongleFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.1.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = DongleFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.5.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = DongleFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.25.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = DongleFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.4.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = DongleFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.1.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = DongleFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.5.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = DongleFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        lineHeight = 1.4.em,
        letterSpacing = 0.5.sp,
    ),
)
