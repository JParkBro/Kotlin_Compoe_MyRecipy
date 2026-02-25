package com.jparkbro.category.editor.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.theme.Border
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Shape
import com.jparkbro.core.model.EditorType
import com.jparkbro.core.ui.component.BackStackTopAppBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun CategoryEditorRoot(
    onNavigateBack: () -> Unit,
    viewModel: CategoryEditorViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    CategoryEditorScreen(
        state = state,
        onAction = { action ->
            when (action) {
                CategoryEditorAction.OnBackClick -> onNavigateBack()
            }
        }
    )
}

@Composable
private fun CategoryEditorScreen(
    state: CategoryEditorState,
    onAction: (CategoryEditorAction) -> Unit
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
                    onNavigationClick = { onAction(CategoryEditorAction.OnBackClick) },
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

    }
}