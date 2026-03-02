package com.jparkbro.core.model.ui

data class TransactionUiModel(
    val id: Long,
    val date: String,
    val time: String,
    val transactionType: String,
    val amount: Long,
    val memo: String?,
    val mainCategoryId: Long?,
    val subCategoryId: Long?,
)
