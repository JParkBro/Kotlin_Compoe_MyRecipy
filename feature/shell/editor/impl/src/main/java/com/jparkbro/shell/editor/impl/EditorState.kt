package com.jparkbro.shell.editor.impl

import androidx.compose.foundation.text.input.TextFieldState
import com.jparkbro.core.model.type.EditorType
import com.jparkbro.core.model.type.TransactionType
import com.jparkbro.core.model.ui.CategoryGroupUiModel
import com.jparkbro.core.model.ui.UiState
import java.time.LocalDate
import java.time.LocalTime

data class EditorState(
    val uiState: UiState = UiState.Loading,
    val editorType: EditorType = EditorType.INSERT,
    val categories: List<CategoryGroupUiModel> = emptyList(),

    val transactionType: TransactionType = TransactionType.INCOME,
    val amount: TextFieldState = TextFieldState(),
    val date: LocalDate = LocalDate.now(),
    val time: LocalTime = LocalTime.now(),
    val selectedMainCategoryId: Long? = null,
    val selectedSubCategoryId: Long? = null,
    val memo: TextFieldState = TextFieldState(),
) {
    val isSaveEnabled: Boolean
        get() = amount.text.toString().toLongOrNull()?.let { it > 0 } == true &&
                selectedMainCategoryId != null &&
                selectedSubCategoryId != null
}
