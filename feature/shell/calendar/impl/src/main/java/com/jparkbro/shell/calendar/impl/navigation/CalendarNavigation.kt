package com.jparkbro.shell.calendar.impl.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.jparkbro.core.navigation.Navigator
import com.jparkbro.shell.calendar.api.CalendarNavKey
import com.jparkbro.shell.calendar.impl.CalendarRoot

fun EntryProviderScope<NavKey>.calendarEntry(
    navigator: Navigator,
    bottomNavigation: @Composable () -> Unit
) {
    entry<CalendarNavKey> {
        CalendarRoot(
            bottomNavigation = bottomNavigation
        )
    }
}
