package com.jparkbro.category.manage.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jparkbro.core.data.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryManageViewModel(
    private val categoryRepository: CategoryRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(CategoryManageState())
    val state = _state.asStateFlow()

    private val _event = Channel<CategoryManageEvent>()
    val event = _event.receiveAsFlow()

    init {
        observeCategories()
    }

    private fun observeCategories() {
        categoryRepository.getCategoriesWithSubs()
            .onEach { categories ->
                _state.update { it.copy(categories = categories) }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: CategoryManageAction) {
        when (action) {
            is CategoryManageAction.OnDeleteClick ->
                _state.update { it.copy(deletingCategoryId = action.id) }
            CategoryManageAction.OnDeleteConfirm ->
                _state.value.deletingCategoryId?.let { deleteCategory(it) }
            CategoryManageAction.OnDeleteDismiss ->
                _state.update { it.copy(deletingCategoryId = null) }
            else -> Unit
        }
    }

    private fun deleteCategory(id: Long) {
        _state.update { it.copy(deletingCategoryId = null) }
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                categoryRepository.deleteCategory(id)
            }.onSuccess {
                _event.send(CategoryManageEvent.DeleteSuccess)
            }.onFailure {
                _event.send(CategoryManageEvent.DeleteFailure)
            }
        }
    }
}
