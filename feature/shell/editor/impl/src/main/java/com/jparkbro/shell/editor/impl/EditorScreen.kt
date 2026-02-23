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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.theme.Border
import com.jparkbro.core.designsystem.theme.MyRecipyTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Shape
import com.jparkbro.core.designsystem.theme.Spacing
import com.jparkbro.core.model.EditorType
import com.jparkbro.core.ui.component.BackStackTopAppBar
import com.jparkbro.shell.editor.impl.component.AmountDisplay
import com.jparkbro.shell.editor.impl.component.CategorySection
import com.jparkbro.shell.editor.impl.component.DateTimeSection
import com.jparkbro.shell.editor.impl.component.TransactionTypeToggle
import org.koin.compose.viewmodel.koinViewModel


@Composable
internal fun EditorRoot(
    onNavigateBack: () -> Unit,
    onNavigateToCategoryManage: () -> Unit,
    viewModel: EditorViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(end = Padding.XXS)
                                .clickable { }
                                .background(
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
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
                TransactionTypeToggle(
                    modifier = Modifier,
                    type = state.transactionType,
                    onTypeClicked = { onAction(EditorAction.OnTypeClicked(it)) }
                )
                AmountDisplay(
                    modifier = Modifier,
                    amount = state.amount,
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
                    onManageClick = { onAction(EditorAction.OnCategoryManageClick) },
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
