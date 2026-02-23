package com.jparkbro.category.manage.impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.jparkbro.category.editor.api.navigateToCategoryEditor
import com.jparkbro.category.manage.api.CategoryManageNavKey
import com.jparkbro.category.manage.api.navigateToCategoryManage
import com.jparkbro.category.manage.impl.CategoryManageRoot
import com.jparkbro.core.navigation.Navigator

fun EntryProviderScope<NavKey>.categoryManageEntry(
    navigator: Navigator,
) {
    entry<CategoryManageNavKey> {
        CategoryManageRoot(
            onNavigateBack = navigator::goBack,
            onNavigateToCategoryEditor = navigator::navigateToCategoryEditor
        )
    }
}
