package com.jparkbro.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.jparkbro.core.database.entity.CategoryEntity
import com.jparkbro.core.database.entity.CategoryWithSubs
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert
    suspend fun insert(category: CategoryEntity)

    @Update
    suspend fun update(category: CategoryEntity)

    @Query("DELETE FROM categories WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM categories WHERE id = :id")
    suspend fun getCategoryById(id: Long): CategoryEntity?

    @Query("SELECT * FROM categories WHERE parentId IS NULL")
    fun getMajorCategories(): Flow<List<CategoryEntity>>

    @Transaction
    @Query("SELECT * FROM categories WHERE parentId IS NULL")
    fun getCategoriesWithSubs(): Flow<List<CategoryWithSubs>>
}
