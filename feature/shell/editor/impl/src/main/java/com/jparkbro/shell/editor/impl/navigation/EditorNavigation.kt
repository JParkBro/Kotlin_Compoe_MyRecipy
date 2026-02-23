package com.jparkbro.shell.editor.impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.jparkbro.category.manage.api.navigateToCategoryManage
import com.jparkbro.core.navigation.Navigator
import com.jparkbro.shell.editor.api.EditorNavKey
import com.jparkbro.shell.editor.impl.EditorRoot

fun EntryProviderScope<NavKey>.editorEntry(
    navigator: Navigator
) {
    entry<EditorNavKey> {
        EditorRoot(
            onNavigateBack = navigator::goBack,
            onNavigateToCategoryManage = navigator::navigateToCategoryManage
        )
    }
}
