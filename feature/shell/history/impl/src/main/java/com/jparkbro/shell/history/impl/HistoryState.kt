package com.jparkbro.shell.history.impl

import com.jparkbro.core.model.type.TransactionType
import com.jparkbro.core.model.ui.CategoryGroupUiModel
import com.jparkbro.core.model.ui.TransactionUiModel
import com.jparkbro.core.model.ui.UiState
import java.time.LocalDate

data class HistoryState(
    val uiState: UiState = UiState.Loading,

    /* 검색조건 */
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val selectedTransactionType: TransactionType? = null,
    val selectedMainCategoryId: Long? = null,
    val selectedSubCategoryId: Long? = null,

    val categories: List<CategoryGroupUiModel> = emptyList(),
    val transactionGroups: List<DayTransactionGroup> = emptyList(),
)

data class DayTransactionGroup(
    val date: String,
    val totalAmount: Long,
    val transactions: List<TransactionUiModel>,
)
