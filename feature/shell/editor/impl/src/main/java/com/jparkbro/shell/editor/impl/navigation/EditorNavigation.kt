package com.jparkbro.shell.editor.impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.jparkbro.category.manage.api.navigateToCategoryManage
import com.jparkbro.core.navigation.Navigator
import com.jparkbro.shell.editor.api.EditorNavKey
import com.jparkbro.shell.editor.impl.EditorRoot
import com.jparkbro.shell.editor.impl.EditorViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun EntryProviderScope<NavKey>.editorEntry(
    navigator: Navigator
) {
    entry<EditorNavKey> { key ->
        EditorRoot(
            onNavigateBack = navigator::goBack,
            onNavigateToCategoryManage = navigator::navigateToCategoryManage,
            viewModel = koinViewModel<EditorViewModel>(
                parameters = { parametersOf(key.type, key.id) }
            ),
        )
    }
}
