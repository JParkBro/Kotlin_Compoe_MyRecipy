package com.jparkbro.shell.editor.impl

import androidx.compose.foundation.text.input.TextFieldState
import com.jparkbro.core.model.EditorType
import com.jparkbro.core.model.TransactionType
import com.jparkbro.core.model.UiState
import java.time.LocalDate
import java.time.LocalTime

data class EditorState(
    val uiState: UiState = UiState.Loading,
    val editorType: EditorType = EditorType.INSERT,
    val date: LocalDate = LocalDate.now(),
    val time: LocalTime = LocalTime.now(),
    val transactionType: TransactionType = TransactionType.INCOME,
    val majorCategory: String = "",
    val middleCategory: String = "",
    val minorCategory: String = "",
    val amount: TextFieldState = TextFieldState(),
    val memo: TextFieldState = TextFieldState(),
    val isApiLoading: Boolean = false,
)
