package com.jparkbro.category.manage.impl

import com.jparkbro.core.model.EditorType

sealed interface CategoryManageAction {
    data object OnBackClick : CategoryManageAction
    data class OnAddClick(val type: EditorType, val id: Long?) : CategoryManageAction
}