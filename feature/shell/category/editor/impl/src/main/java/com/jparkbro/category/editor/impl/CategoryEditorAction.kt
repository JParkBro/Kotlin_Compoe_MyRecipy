package com.jparkbro.category.editor.impl

import com.jparkbro.core.model.type.CategoryType

sealed interface CategoryEditorAction {
    data object OnBackClick : CategoryEditorAction
    data class OnTypeClicked(val type: CategoryType) : CategoryEditorAction
    data class OnParentCategorySelected(val id: Long) : CategoryEditorAction
    data class OnIconSelected(val iconName: String) : CategoryEditorAction
    data object OnSaveClick : CategoryEditorAction
}