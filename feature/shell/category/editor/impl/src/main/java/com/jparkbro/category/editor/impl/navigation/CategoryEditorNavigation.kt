package com.jparkbro.category.editor.impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.jparkbro.category.editor.api.CategoryEditorNavKey
import com.jparkbro.category.editor.impl.CategoryEditorRoot
import com.jparkbro.category.editor.impl.CategoryEditorViewModel
import com.jparkbro.core.navigation.Navigator
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun EntryProviderScope<NavKey>.categoryEditorEntry(
    navigator: Navigator,
) {
    entry<CategoryEditorNavKey> { key ->
        CategoryEditorRoot(
            onNavigateBack = navigator::goBack,
            viewModel = koinViewModel<CategoryEditorViewModel>(
                parameters = { parametersOf(key.type, key.id) }
            ),
        )
    }
}
