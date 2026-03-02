package com.jparkbro.shell.editor.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jparkbro.core.data.repository.CategoryRepository
import com.jparkbro.core.data.repository.TransactionRepository
import com.jparkbro.core.model.type.EditorType
import com.jparkbro.core.model.type.TransactionType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class EditorViewModel(
    private val editorType: EditorType,
    private val transactionId: Long?,
    private val categoryRepository: CategoryRepository,
    private val transactionRepository: TransactionRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(EditorState(editorType = editorType))
    val state = _state.asStateFlow()

    private val _event = Channel<EditorEvent>()
    val event = _event.receiveAsFlow()

    init {
        observeCategories()
        if (editorType == EditorType.UPDATE && transactionId != null) {
            loadTransaction(transactionId)
        }
    }

    private fun observeCategories() {
        categoryRepository.getCategoriesWithSubs()
            .onEach { categories ->
                _state.update { current ->
                    val defaultMain = categories.firstOrNull()?.parent
                    val defaultSub = categories.firstOrNull()?.children?.firstOrNull()
                    current.copy(
                        categories = categories,
                        selectedMainCategoryId = current.selectedMainCategoryId ?: defaultMain?.id,
                        selectedSubCategoryId = current.selectedSubCategoryId ?: defaultSub?.id,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun loadTransaction(id: Long) {
        viewModelScope.launch {
            transactionRepository.getTransactionById(id)?.let { transaction ->
                _state.update {
                    it.copy(
                        transactionType = TransactionType.valueOf(transaction.transactionType),
                        date = LocalDate.parse(transaction.date),
                        time = LocalTime.parse(transaction.time),
                        selectedMainCategoryId = transaction.mainCategoryId,
                        selectedSubCategoryId = transaction.subCategoryId,
                    )
                }
                _state.value.amount.edit { replace(0, length, transaction.amount.toString()) }
                transaction.memo?.let { memo ->
                    _state.value.memo.edit { replace(0, length, memo) }
                }
            }
        }
    }

    fun onAction(action: EditorAction) {
        when (action) {
            is EditorAction.OnTypeClicked -> _state.update { it.copy(transactionType = action.type) }
            is EditorAction.OnDateSelected -> _state.update { it.copy(date = action.date) }
            is EditorAction.OnTimeSelected -> _state.update { it.copy(time = action.time) }
            is EditorAction.OnMainCategorySelected -> selectMainCategory(action.id)
            is EditorAction.OnSubCategorySelected -> _state.update { it.copy(selectedSubCategoryId = action.id) }
            EditorAction.OnSaveClick -> saveTransaction()
            else -> Unit
        }
    }

    private fun selectMainCategory(id: Long) {
        val firstSub = _state.value.categories
            .find { it.parent.id == id }
            ?.children?.firstOrNull()

        _state.update {
            it.copy(
                selectedMainCategoryId = id,
                selectedSubCategoryId = firstSub?.id,
            )
        }
    }

    private fun saveTransaction() {
        val current = _state.value
        val amount = current.amount.text.toString().toLongOrNull() ?: return
        val timeStr = current.time.format(DateTimeFormatter.ofPattern("HH:mm"))

        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                when (editorType) {
                    EditorType.INSERT -> transactionRepository.insertTransaction(
                        date = current.date.toString(),
                        time = timeStr,
                        transactionType = current.transactionType.name,
                        amount = amount,
                        memo = current.memo.text.toString().ifBlank { null },
                        mainCategoryId = current.selectedMainCategoryId,
                        subCategoryId = current.selectedSubCategoryId,
                    )
                    EditorType.UPDATE -> transactionId?.let {
                        transactionRepository.updateTransaction(
                            id = it,
                            date = current.date.toString(),
                            time = timeStr,
                            transactionType = current.transactionType.name,
                            amount = amount,
                            memo = current.memo.text.toString().ifBlank { null },
                            mainCategoryId = current.selectedMainCategoryId,
                            subCategoryId = current.selectedSubCategoryId,
                        )
                    }
                }
            }.onSuccess {
                _event.send(EditorEvent.SaveSuccess)
            }.onFailure {
                _event.send(EditorEvent.SaveFailure)
            }
        }
    }
}
