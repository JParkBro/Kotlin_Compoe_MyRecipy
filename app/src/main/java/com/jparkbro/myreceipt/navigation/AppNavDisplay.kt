package com.jparkbro.myreceipt.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.jparkbro.category.editor.impl.navigation.categoryEditorEntry
import com.jparkbro.category.manage.impl.navigation.categoryManageEntry
import com.jparkbro.core.navigation.NavigationState
import com.jparkbro.core.navigation.Navigator
import com.jparkbro.core.navigation.toEntries
import com.jparkbro.shell.calendar.impl.navigation.calendarEntry
import com.jparkbro.shell.editor.impl.navigation.editorEntry
import com.jparkbro.shell.history.impl.navigation.historyEntry
import com.jparkbro.shell.report.impl.navigation.reportEntry
import com.jparkbro.shell.settings.impl.navigation.settingsEntry

@Composable
fun AppNavDisplay(
    bottomNavigation: @Composable () -> Unit,
    navigationState: NavigationState,
    navigator: Navigator,
    modifier: Modifier = Modifier,
) {
    val entryProvider = entryProvider<NavKey> {
        historyEntry(navigator, bottomNavigation)
        calendarEntry(navigator, bottomNavigation)
        editorEntry(navigator)
        reportEntry(navigator, bottomNavigation)
        settingsEntry(navigator, bottomNavigation)
        categoryManageEntry(navigator)
        categoryEditorEntry(navigator)
    }

    NavDisplay(
        entries = navigationState.toEntries(entryProvider),
        onBack = navigator::goBack,
        modifier = modifier,
    )
}