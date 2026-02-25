package com.jparkbro.category.editor.impl

import com.jparkbro.core.model.EditorType
import com.jparkbro.core.model.UiState

data class CategoryEditorState(
    val uiState: UiState = UiState.Loading,
    val editorType: EditorType = EditorType.INSERT,
)
