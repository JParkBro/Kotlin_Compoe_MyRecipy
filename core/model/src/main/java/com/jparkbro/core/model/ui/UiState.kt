package com.jparkbro.core.model.ui

sealed interface UiState {
    data object Loading : UiState
    data object Success : UiState
    data object Error : UiState
}
