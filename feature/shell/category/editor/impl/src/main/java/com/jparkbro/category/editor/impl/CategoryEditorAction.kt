package com.jparkbro.category.editor.impl

sealed interface CategoryEditorAction {
    data object OnBackClick : CategoryEditorAction

}