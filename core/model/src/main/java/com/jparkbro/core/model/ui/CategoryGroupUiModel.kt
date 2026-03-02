package com.jparkbro.core.model.ui

data class CategoryGroupUiModel(
    val parent: CategoryUiModel,
    val children: List<CategoryUiModel>,
)
