package com.jparkbro.core.data.repository

import com.jparkbro.core.database.dao.TransactionDao
import com.jparkbro.core.database.entity.TransactionEntity
import com.jparkbro.core.model.ui.TransactionUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao,
) : TransactionRepository {

    override suspend fun getTransactionById(id: Long): TransactionUiModel? {
        return transactionDao.getById(id)?.toUiModel()
    }

    override fun searchTransactions(
        startDate: String?,
        endDate: String?,
        transactionType: String?,
        categoryId: Long?,
    ): Flow<List<TransactionUiModel>> {
        return transactionDao.search(startDate, endDate, transactionType, categoryId)
            .map { list -> list.map { it.toUiModel() } }
    }

    override suspend fun insertTransaction(
        date: String,
        time: String,
        transactionType: String,
        amount: Long,
        memo: String?,
        mainCategoryId: Long?,
        subCategoryId: Long?,
    ) {
        transactionDao.insert(
            TransactionEntity(
                date = date,
                time = time,
                transactionType = transactionType,
                amount = amount,
                memo = memo,
                mainCategoryId = mainCategoryId,
                subCategoryId = subCategoryId,
            )
        )
    }

    override suspend fun updateTransaction(
        id: Long,
        date: String,
        time: String,
        transactionType: String,
        amount: Long,
        memo: String?,
        mainCategoryId: Long?,
        subCategoryId: Long?,
    ) {
        transactionDao.update(
            TransactionEntity(
                id = id,
                date = date,
                time = time,
                transactionType = transactionType,
                amount = amount,
                memo = memo,
                mainCategoryId = mainCategoryId,
                subCategoryId = subCategoryId,
            )
        )
    }

    override suspend fun deleteTransaction(id: Long) {
        transactionDao.deleteById(id)
    }

    private fun TransactionEntity.toUiModel() = TransactionUiModel(
        id = id,
        date = date,
        time = time,
        transactionType = transactionType,
        amount = amount,
        memo = memo,
        mainCategoryId = mainCategoryId,
        subCategoryId = subCategoryId,
    )
}
