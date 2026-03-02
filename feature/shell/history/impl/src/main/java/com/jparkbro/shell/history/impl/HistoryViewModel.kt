package com.jparkbro.shell.history.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jparkbro.core.data.repository.CategoryRepository
import com.jparkbro.core.data.repository.TransactionRepository
import com.jparkbro.core.model.type.TransactionType
import com.jparkbro.core.model.ui.TransactionUiModel
import com.jparkbro.core.model.ui.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class HistoryViewModel(
    private val transactionRepository: TransactionRepository,
    private val categoryRepository: CategoryRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryState())
    val state = _state.asStateFlow()

    private val _event = Channel<HistoryEvent>()
    val event = _event.receiveAsFlow()

    private val _filter = MutableStateFlow(SearchFilter())

    init {
        observeTransactions()
        observeCategories()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeTransactions() {
        _filter
            .flatMapLatest { filter ->
                transactionRepository.searchTransactions(
                    startDate = filter.startDate?.toString(),
                    endDate = filter.endDate?.toString(),
                    transactionType = filter.transactionType?.name,
                    categoryId = filter.categoryId,
                )
            }
            .onEach { transactions ->
                _state.update { it.copy(
                    transactionGroups = transactions.groupByDate(),
                    uiState = UiState.Success,
                ) }
            }
            .launchIn(viewModelScope)
    }

    private fun observeCategories() {
        categoryRepository.getCategoriesWithSubs()
            .onEach { categories ->
                _state.update { it.copy(categories = categories) }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: HistoryAction) {
        when (action) {
            is HistoryAction.OnTransactionTypeSelected -> {
                _state.update { it.copy(selectedTransactionType = action.type) }
                _filter.update { it.copy(transactionType = action.type) }
            }
            is HistoryAction.OnStartDateSelected -> {
                _state.update { it.copy(startDate = action.date) }
                _filter.update { it.copy(startDate = action.date) }
            }
            is HistoryAction.OnEndDateSelected -> {
                _state.update { it.copy(endDate = action.date) }
                _filter.update { it.copy(endDate = action.date) }
            }
            is HistoryAction.OnMainCategorySelected -> {
                _state.update { it.copy(selectedMainCategoryId = action.id, selectedSubCategoryId = null) }
                _filter.update { it.copy(categoryId = action.id) }
            }
            is HistoryAction.OnSubCategorySelected -> {
                _state.update { it.copy(selectedSubCategoryId = action.id) }
                _filter.update { it.copy(categoryId = action.id ?: _state.value.selectedMainCategoryId) }
            }
            is HistoryAction.OnTransactionDeleteClick ->
                _state.update { it.copy(deletingTransactionId = action.id) }
            HistoryAction.OnTransactionDeleteConfirm ->
                _state.value.deletingTransactionId?.let { deleteTransaction(it) }
            HistoryAction.OnTransactionDeleteDismiss ->
                _state.update { it.copy(deletingTransactionId = null) }
            else -> Unit
        }
    }

    private fun deleteTransaction(id: Long) {
        _state.update { it.copy(deletingTransactionId = null) }
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                transactionRepository.deleteTransaction(id)
            }.onSuccess {
                _event.send(HistoryEvent.DeleteSuccess)
            }.onFailure {
                _event.send(HistoryEvent.DeleteFailure)
            }
        }
    }

    private fun List<TransactionUiModel>.groupByDate(): List<DayTransactionGroup> {
        return groupBy { it.date }.map { (date, list) ->
            val income = list.filter { it.transactionType == TransactionType.INCOME.name }.sumOf { it.amount }
            val expense = list.filter { it.transactionType == TransactionType.EXPENSE.name }.sumOf { it.amount }
            DayTransactionGroup(
                date = date,
                totalAmount = income - expense,
                transactions = list,
            )
        }
    }

    private data class SearchFilter(
        val startDate: LocalDate? = null,
        val endDate: LocalDate? = null,
        val transactionType: TransactionType? = null,
        val categoryId: Long? = null,
    )
}
