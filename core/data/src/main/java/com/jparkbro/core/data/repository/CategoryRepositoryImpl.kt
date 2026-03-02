package com.jparkbro.core.data.repository

import com.jparkbro.core.database.dao.CategoryDao
import com.jparkbro.core.database.entity.CategoryEntity
import com.jparkbro.core.model.ui.CategoryGroupUiModel
import com.jparkbro.core.model.ui.CategoryUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl(
    private val categoryDao: CategoryDao,
) : CategoryRepository {

    override suspend fun getCategoryById(id: Long): CategoryUiModel? {
        return categoryDao.getCategoryById(id)?.toUiModel()
    }

    override suspend fun insertCategory(title: String, parentId: Long?, iconName: String) {
        categoryDao.insert(
            CategoryEntity(
                parentId = parentId,
                title = title,
                iconName = iconName,
            )
        )
    }

    override suspend fun updateCategory(id: Long, title: String, parentId: Long?, iconName: String) {
        categoryDao.update(
            CategoryEntity(
                id = id,
                parentId = parentId,
                title = title,
                iconName = iconName,
            )
        )
    }

    override suspend fun deleteCategory(id: Long) {
        categoryDao.delete(id)
    }

    /** 카테고리 등록 화면에서 사용 */
    override fun getMajorCategories(): Flow<List<CategoryUiModel>> {
        return categoryDao.getMajorCategories().map { entities ->
            entities.map { it.toUiModel() }
        }
    }

    override fun getCategoriesWithSubs(): Flow<List<CategoryGroupUiModel>> {
        return categoryDao.getCategoriesWithSubs().map { list ->
            list.map { group ->
                CategoryGroupUiModel(
                    parent = group.parent.toUiModel(),
                    children = group.children.map { it.toUiModel() },
                )
            }
        }
    }

    private fun CategoryEntity.toUiModel() = CategoryUiModel(
        id = id,
        parentId = parentId,
        title = title,
        iconName = iconName,
    )
}
