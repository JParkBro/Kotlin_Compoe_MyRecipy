package com.jparkbro.category.editor.impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.jparkbro.category.editor.api.CategoryEditorNavKey
import com.jparkbro.category.editor.impl.CategoryEditorRoot
import com.jparkbro.core.navigation.Navigator

fun EntryProviderScope<NavKey>.categoryEditorEntry(
    navigator: Navigator,
) {
    entry<CategoryEditorNavKey> {
        CategoryEditorRoot(

        )
    }
}
