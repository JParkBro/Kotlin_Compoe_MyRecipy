package com.jparkbro.category.manage.impl

import com.jparkbro.core.model.ui.CategoryGroupUiModel
import com.jparkbro.core.model.ui.UiState

data class CategoryManageState(
    val uiState: UiState = UiState.Loading,
    val categories: List<CategoryGroupUiModel> = emptyList(),
    val deletingCategoryId: Long? = null,
)
