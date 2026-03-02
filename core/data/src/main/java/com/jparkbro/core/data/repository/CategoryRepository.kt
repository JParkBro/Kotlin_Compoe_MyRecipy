package com.jparkbro.core.data.repository

import com.jparkbro.core.model.ui.CategoryGroupUiModel
import com.jparkbro.core.model.ui.CategoryUiModel
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategoryById(id: Long): CategoryUiModel?
    suspend fun insertCategory(title: String, parentId: Long?, iconName: String)
    suspend fun updateCategory(id: Long, title: String, parentId: Long?, iconName: String)
    suspend fun deleteCategory(id: Long)
    fun getMajorCategories(): Flow<List<CategoryUiModel>>
    fun getCategoriesWithSubs(): Flow<List<CategoryGroupUiModel>>
}
