package com.jparkbro.shell.settings.impl.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.jparkbro.core.navigation.Navigator
import com.jparkbro.shell.settings.api.SettingsNavKey
import com.jparkbro.shell.settings.impl.SettingsScreen

fun EntryProviderScope<NavKey>.settingsEntry(
    navigator: Navigator,
    bottomNavigation: @Composable () -> Unit
) {
    entry<SettingsNavKey> {
        SettingsScreen()
    }
}
