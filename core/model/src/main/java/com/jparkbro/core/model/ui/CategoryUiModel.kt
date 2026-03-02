package com.jparkbro.core.model.ui

data class CategoryUiModel(
    val id: Long,
    val parentId: Long?,
    val title: String,
    val iconName: String,
)
