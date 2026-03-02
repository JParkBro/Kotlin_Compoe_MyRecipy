package com.jparkbro.shell.editor.impl

import com.jparkbro.core.model.type.TransactionType
import java.time.LocalDate
import java.time.LocalTime

sealed interface EditorAction {
    data object OnBackClick : EditorAction
    data object OnSaveClick : EditorAction
    data object OnCategoryManageClick : EditorAction
    data object OnSubCategoryAddClick : EditorAction // TODO 추가기능
    data class OnTypeClicked(val type: TransactionType) : EditorAction
    data class OnDateSelected(val date: LocalDate) : EditorAction
    data class OnTimeSelected(val time: LocalTime) : EditorAction
    data class OnMainCategorySelected(val id: Long) : EditorAction
    data class OnSubCategorySelected(val id: Long) : EditorAction
}