package com.jparkbro.shell.editor.impl.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.jparkbro.core.navigation.Navigator
import com.jparkbro.shell.editor.api.EditorNavKey
import com.jparkbro.shell.editor.impl.EditorScreen

fun EntryProviderScope<NavKey>.editorEntry(
    navigator: Navigator,
    bottomNavigation: @Composable () -> Unit
) {
    entry<EditorNavKey> {
        EditorScreen()
    }
}
