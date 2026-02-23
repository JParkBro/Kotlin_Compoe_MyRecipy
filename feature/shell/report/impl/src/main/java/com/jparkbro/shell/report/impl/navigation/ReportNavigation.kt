package com.jparkbro.shell.report.impl.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.jparkbro.core.navigation.Navigator
import com.jparkbro.shell.report.api.ReportNavKey
import com.jparkbro.shell.report.impl.ReportRoot

fun EntryProviderScope<NavKey>.reportEntry(
    navigator: Navigator,
    bottomNavigation: @Composable () -> Unit
) {
    entry<ReportNavKey> {
        ReportRoot(
            bottomNavigation = bottomNavigation
        )
    }
}
