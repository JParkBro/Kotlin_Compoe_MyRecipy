package com.jparkbro.core.ui.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp

data class GridInfo(
    val columns: Int,
    val itemWidth: Dp,
)

@Composable
fun rememberGridInfo(
    availableWidth: Dp,
    spacing: Dp,
    defaultItemWidth: Dp,
    horizontalPadding: Dp = Dp(0f),
    minColumns: Int = 1,
    maxColumns: Int = Int.MAX_VALUE,
): GridInfo {
    return remember(availableWidth, horizontalPadding, spacing, defaultItemWidth) {
        val usableWidth = availableWidth - horizontalPadding

        val columns = ((usableWidth + spacing) / (defaultItemWidth + spacing))
            .toInt()
            .coerceIn(minColumns, maxColumns)

        val itemWidth = (usableWidth - spacing * (columns - 1)) / columns

        GridInfo(columns = columns, itemWidth = itemWidth)
    }
}
