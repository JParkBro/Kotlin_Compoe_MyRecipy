package com.jparkbro.category.editor.impl

sealed interface CategoryEditorEvent {
    data object SaveSuccess : CategoryEditorEvent
    data object SaveFailure : CategoryEditorEvent
}