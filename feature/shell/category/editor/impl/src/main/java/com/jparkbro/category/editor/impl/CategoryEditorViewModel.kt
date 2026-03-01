package com.jparkbro.category.editor.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jparkbro.core.data.repository.CategoryRepository
import com.jparkbro.core.model.type.CategoryType
import com.jparkbro.core.model.type.EditorType
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryEditorViewModel(
    private val editorType: EditorType,
    private val categoryId: Long?,
    private val categoryRepository: CategoryRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(CategoryEditorState(editorType = editorType))
    val state = _state.asStateFlow()

    private val _event = Channel<CategoryEditorEvent>()
    val event = _event.receiveAsFlow()

    init {
        observeMajorCategories()
        if (editorType == EditorType.UPDATE && categoryId != null) {
            loadCategory(categoryId)
        }
    }

    private fun observeMajorCategories() {
        categoryRepository.getMajorCategories()
            .onEach { categories ->
                _state.update { it.copy(parentCategories = categories) }
            }
            .launchIn(viewModelScope)
    }

    private fun loadCategory(id: Long) {
        viewModelScope.launch {
            categoryRepository.getCategoryById(id)?.let { category ->
                _state.update {
                    it.copy(
                        categoryType = if (category.parentId == null) CategoryType.MAIN else CategoryType.SUB,
                        selectedParentCategory = category.parentId,
                        selectedIconName = category.iconName,
                    )
                }
                _state.value.categoryName.edit { replace(0, length, category.title) }
            }
        }
    }

    fun onAction(action: CategoryEditorAction) {
        when (action) {
            is CategoryEditorAction.OnTypeClicked -> {
                _state.update { it.copy(categoryType = action.type) }
            }
            is CategoryEditorAction.OnParentCategorySelected -> {
                _state.update { it.copy(selectedParentCategory = action.id) }
            }
            is CategoryEditorAction.OnIconSelected -> {
                _state.update { it.copy(selectedIconName = action.iconName) }
            }
            is CategoryEditorAction.OnSaveClick -> saveCategory()
            else -> Unit
        }
    }

    private fun saveCategory() {
        val current = _state.value
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                when (editorType) {
                    EditorType.INSERT -> categoryRepository.insertCategory(
                        title = current.categoryName.text.toString(),
                        parentId = current.selectedParentCategory,
                        iconName = current.selectedIconName,
                    )
                    EditorType.UPDATE -> categoryId?.let {
                        categoryRepository.updateCategory(
                            id = it,
                            title = current.categoryName.text.toString(),
                            parentId = current.selectedParentCategory,
                            iconName = current.selectedIconName,
                        )
                    }
                }
            }.onSuccess {
                _event.send(CategoryEditorEvent.SaveSuccess)
            }.onFailure {
                _event.send(CategoryEditorEvent.SaveFailure)
            }
        }
    }
}
