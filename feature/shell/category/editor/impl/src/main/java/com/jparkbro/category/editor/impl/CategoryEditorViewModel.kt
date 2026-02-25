package com.jparkbro.category.editor.impl

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoryEditorViewModel(

) : ViewModel() {

    private val _state = MutableStateFlow(CategoryEditorState())
    val state = _state.asStateFlow()

    init {

    }

    fun onAction(action: CategoryEditorAction) {
        when (action) {

            else -> Unit
        }
    }

}