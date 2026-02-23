package com.jparkbro.shell.editor.impl

import com.jparkbro.core.model.TransactionType
import java.time.LocalDate
import java.time.LocalTime

sealed interface EditorAction {
    data object OnBackClick : EditorAction
    data object OnCategoryManageClick : EditorAction
    data object OnSubCategoryAddClick : EditorAction // TODO 추가기능
    data class OnTypeClicked(val type: TransactionType) : EditorAction
    data class OnDateSelected(val date: LocalDate) : EditorAction
    data class OnTimeSelected(val time: LocalTime) : EditorAction
}