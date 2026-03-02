package com.jparkbro.shell.history.impl

import com.jparkbro.core.model.type.TransactionType
import java.time.LocalDate

sealed interface HistoryAction {
    data class OnStartDateSelected(val date: LocalDate) : HistoryAction
    data class OnEndDateSelected(val date: LocalDate) : HistoryAction
    data class OnTransactionTypeSelected(val type: TransactionType?) : HistoryAction
    data class OnMainCategorySelected(val id: Long?) : HistoryAction
    data class OnSubCategorySelected(val id: Long?) : HistoryAction
    data class OnTransactionClick(val id: Long) : HistoryAction
    data class OnTransactionEditClick(val id: Long) : HistoryAction
    data class OnTransactionDeleteClick(val id: Long) : HistoryAction
    data object OnTransactionDeleteConfirm : HistoryAction
    data object OnTransactionDeleteDismiss : HistoryAction
}
