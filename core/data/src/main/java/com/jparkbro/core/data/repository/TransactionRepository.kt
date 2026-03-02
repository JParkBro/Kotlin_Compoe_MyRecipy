package com.jparkbro.core.data.repository

import com.jparkbro.core.model.ui.TransactionUiModel
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun getTransactionById(id: Long): TransactionUiModel?
    fun searchTransactions(
        startDate: String? = null,
        endDate: String? = null,
        transactionType: String? = null,
        categoryId: Long? = null,
    ): Flow<List<TransactionUiModel>>
    suspend fun insertTransaction(
        date: String,
        time: String,
        transactionType: String,
        amount: Long,
        memo: String?,
        mainCategoryId: Long?,
        subCategoryId: Long?,
    )
    suspend fun updateTransaction(
        id: Long,
        date: String,
        time: String,
        transactionType: String,
        amount: Long,
        memo: String?,
        mainCategoryId: Long?,
        subCategoryId: Long?,
    )
    suspend fun deleteTransaction(id: Long)
}
