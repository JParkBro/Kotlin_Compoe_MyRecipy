package com.jparkbro.category.manage.impl

import com.jparkbro.core.model.type.EditorType

sealed interface CategoryManageAction {
    data object OnBackClick : CategoryManageAction
    data class OnAddClick(val type: EditorType, val id: Long?) : CategoryManageAction
    data class OnUpdateClick(val type: EditorType, val id: Long) : CategoryManageAction
    data class OnDeleteClick(val id: Long) : CategoryManageAction
    data object OnDeleteConfirm : CategoryManageAction
    data object OnDeleteDismiss : CategoryManageAction
}