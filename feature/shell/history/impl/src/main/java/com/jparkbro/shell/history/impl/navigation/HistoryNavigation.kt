package com.jparkbro.shell.history.impl.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.jparkbro.core.navigation.Navigator
import com.jparkbro.shell.history.api.HistoryNavKey
import com.jparkbro.shell.history.impl.HistoryRoot

fun EntryProviderScope<NavKey>.historyEntry(
    navigator: Navigator,
    bottomNavigation: @Composable () -> Unit,
) {
    entry<HistoryNavKey> {
        HistoryRoot(
            bottomNavigation = bottomNavigation
        )
    }
}
