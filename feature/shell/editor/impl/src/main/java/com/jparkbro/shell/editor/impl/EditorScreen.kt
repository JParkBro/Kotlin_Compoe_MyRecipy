package com.jparkbro.shell.editor.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jparkbro.core.ui.util.ObserveAsEvents
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.theme.Border
import com.jparkbro.core.designsystem.theme.MyRecipyTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Shape
import com.jparkbro.core.designsystem.theme.Spacing
import com.jparkbro.core.model.type.EditorType
import com.jparkbro.core.ui.component.BackStackTopAppBar
import com.jparkbro.shell.editor.impl.component.AmountDisplay
import com.jparkbro.shell.editor.impl.component.CategorySection
import com.jparkbro.shell.editor.impl.component.DateTimeSection
import com.jparkbro.shell.editor.impl.component.MemoSection
import com.jparkbro.shell.editor.impl.component.TransactionTypeSelector
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun EditorRoot(
    onNavigateBack: () -> Unit,
    onNavigateToCategoryManage: () -> Unit,
    viewModel: EditorViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val saveSuccessMessage = stringResource(R.string.save_success)
    val saveFailureMessage = stringResource(R.string.save_failure)
    ObserveAsEvents(viewModel.event) { event ->
        when (event) {
            EditorEvent.SaveSuccess -> {
                android.widget.Toast.makeText(context, saveSuccessMessage, android.widget.Toast.LENGTH_SHORT).show()
                onNavigateBack()
            }
            EditorEvent.SaveFailure -> {
                android.widget.Toast.makeText(context, saveFailureMessage, android.widget.Toast.LENGTH_SHORT).show()
            }
        }
    }

    EditorScreen(
        state = state,
        onAction = { action ->
            when (action) {
                EditorAction.OnBackClick -> onNavigateBack()
                EditorAction.OnCategoryManageClick -> onNavigateToCategoryManage()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun EditorScreen(
    state: EditorState,
    onAction: (EditorAction) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            Column {
                BackStackTopAppBar(
                    titleRes = when (state.editorType) {
                        EditorType.INSERT -> R.string.editor_register_title
                        EditorType.UPDATE -> R.string.editor_edit_title
                    },
                    actions = {
                        Text(
                            text = stringResource(R.string.save),
                            style = MaterialTheme.typography.labelLarge,
                            color = if (state.isSaveEnabled) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                            modifier = Modifier
                                .padding(end = Padding.XXS)
                                .clickable(enabled = state.isSaveEnabled) { onAction(EditorAction.OnSaveClick) }
                                .background(
                                    color = if (state.isSaveEnabled) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                                            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f),
                                    shape = RoundedCornerShape(Shape.M)
                                )
                                .padding(horizontal = Padding.XS, vertical = Padding.XXS)
                        )
                    },
                    onNavigationClick = { onAction(EditorAction.OnBackClick) },
                )
                HorizontalDivider(
                    thickness = Border.XS,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() }
                )
            },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = Padding.XS),
            verticalArrangement = Arrangement.spacedBy(Spacing.XS)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = Padding.XS),
                verticalArrangement = Arrangement.spacedBy(Spacing.S)
            ) {
                TransactionTypeSelector(
                    modifier = Modifier,
                    type = state.transactionType,
                    onTypeClicked = { onAction(EditorAction.OnTypeClicked(it)) }
                )
                AmountDisplay(
                    modifier = Modifier,
                    amountState = state.amount,
                    onDone = { focusManager.clearFocus() }
                )
                DateTimeSection(
                    modifier = Modifier,
                    date = state.date,
                    time = state.time,
                    onDateSelected = { onAction(EditorAction.OnDateSelected(it)) },
                    onTimeSelected = { onAction(EditorAction.OnTimeSelected(it)) },
                )
                CategorySection(
                    modifier = Modifier,
                    categories = state.categories,
                    selectedMainCategoryId = state.selectedMainCategoryId,
                    selectedSubCategoryId = state.selectedSubCategoryId,
                    onMainCategorySelected = { onAction(EditorAction.OnMainCategorySelected(it)) },
                    onSubCategorySelected = { onAction(EditorAction.OnSubCategorySelected(it)) },
                    onManageClick = { onAction(EditorAction.OnCategoryManageClick) },
                )
                MemoSection(
                    modifier = Modifier,
                    memoState = state.memo
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditorScreenPreview() {
    MyRecipyTheme {
        EditorScreen(
            state = EditorState(),
            onAction = {}
        )
    }
}
