package com.jparkbro.myrecipy.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class TopLevelNavItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @param:StringRes val iconTextId: Int,
    @param:StringRes val titleTextId: Int,
)

val EDITOR = TopLevelNavItem(
    selectedIcon = Icons,
    unselectedIcon = TODO(),
    iconTextId = TODO(),
    titleTextId = TODO()
)

val HISTORY = TopLevelNavItem(

)

val CALENDAR = TopLevelNavItem(

)

val REPORT = TopLevelNavItem(

)

val SETTINGS = TopLevelNavItem(

)