package com.jparkbro.category.editor.impl

import androidx.compose.foundation.text.input.TextFieldState
import com.jparkbro.core.designsystem.icon.CategoryIcons
import com.jparkbro.core.model.type.CategoryType
import com.jparkbro.core.model.ui.CategoryUiModel
import com.jparkbro.core.model.type.EditorType
import com.jparkbro.core.model.ui.UiState

data class CategoryEditorState(
    val uiState: UiState = UiState.Loading,
    val editorType: EditorType = EditorType.INSERT,

    val categoryName: TextFieldState = TextFieldState(),
    val categoryType: CategoryType = CategoryType.MAIN,
    val parentCategories: List<CategoryUiModel> = emptyList(),
    val selectedParentCategory: Long? = null,
    val selectedIconName: String = CategoryIcons.first(),
) {
    val isSaveEnabled: Boolean
        get() = categoryName.text.isNotEmpty() &&
                (categoryType == CategoryType.MAIN || selectedParentCategory != null)
}
