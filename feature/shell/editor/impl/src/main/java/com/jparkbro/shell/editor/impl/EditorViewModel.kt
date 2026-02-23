package com.jparkbro.shell.editor.impl

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EditorViewModel(

) : ViewModel() {

    private val _state = MutableStateFlow(EditorState())
    val state = _state.asStateFlow()

    init {

    }

    fun onAction(action: EditorAction) {
        when (action) {
            is EditorAction.OnTypeClicked -> { _state.update { it.copy(transactionType = action.type) } }
            is EditorAction.OnDateSelected -> { _state.update { it.copy(date = action.date) } }
            is EditorAction.OnTimeSelected -> { _state.update { it.copy(time = action.time) } }
            else -> Unit
        }
    }
}