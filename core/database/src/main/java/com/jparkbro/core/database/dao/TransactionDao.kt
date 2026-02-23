package com.jparkbro.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jparkbro.core.database.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert
    suspend fun insert(transaction: TransactionEntity)

    @Update
    suspend fun update(transaction: TransactionEntity)

    @Delete
    suspend fun delete(transaction: TransactionEntity)

    // 수정에서 사용, 상세 조회
    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getById(id: Long): TransactionEntity?

    // 전체 조회
    @Query("SELECT * FROM transactions ORDER BY date DESC, time DESC")
    fun getAll(): Flow<List<TransactionEntity>>

    // 복합 조건 검색 (null 인 조건은 무시됨)
    @Query("""
        SELECT * FROM transactions
        WHERE (:startDate IS NULL OR date >= :startDate)
        AND (:endDate IS NULL OR date <= :endDate)
        AND (:transactionType IS NULL OR transactionType = :transactionType)
        AND (:categoryId IS NULL OR majorCategoryId = :categoryId OR middleCategoryId = :categoryId OR minorCategoryId = :categoryId)
        ORDER BY date DESC, time DESC
    """)
    fun search(
        startDate: String? = null,
        endDate: String? = null,
        transactionType: String? = null,
        categoryId: Long? = null,
    ): Flow<List<TransactionEntity>>

    // 특정 날짜 조회
    @Query("SELECT * FROM transactions WHERE date = :date ORDER BY time DESC")
    fun getByDate(date: String): Flow<List<TransactionEntity>>
}
