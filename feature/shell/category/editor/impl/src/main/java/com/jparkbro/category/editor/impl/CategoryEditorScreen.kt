package com.jparkbro.category.editor.impl

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jparkbro.category.editor.impl.component.CategoryTitleForm
import com.jparkbro.category.editor.impl.component.CategoryTypeSelector
import com.jparkbro.category.editor.impl.component.IconSelectorSection
import com.jparkbro.category.editor.impl.component.ParentCategorySelector
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.theme.Border
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Shape
import com.jparkbro.core.designsystem.theme.Spacing
import com.jparkbro.core.model.type.CategoryType
import com.jparkbro.core.model.type.EditorType
import com.jparkbro.core.ui.component.BackStackTopAppBar
import com.jparkbro.core.ui.util.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun CategoryEditorRoot(
    onNavigateBack: () -> Unit,
    viewModel: CategoryEditorViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    val saveSuccessMessage = stringResource(R.string.save_success)
    val saveFailureMessage = stringResource(R.string.save_failure)
    ObserveAsEvents(viewModel.event) { event ->
        when (event) {
            CategoryEditorEvent.SaveSuccess -> {
                Toast.makeText(context, saveSuccessMessage, Toast.LENGTH_SHORT).show()
                onNavigateBack()
            }
            CategoryEditorEvent.SaveFailure -> {
                Toast.makeText(context, saveFailureMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    CategoryEditorScreen(
        state = state,
        onAction = { action ->
            when (action) {
                CategoryEditorAction.OnBackClick -> onNavigateBack()
                else -> Unit
            }
            viewModel.onAction(action)
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
                            color = if (state.isSaveEnabled) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                            },
                            modifier = Modifier
                                .padding(end = Padding.XXS)
                                .clickable(enabled = state.isSaveEnabled) { onAction(CategoryEditorAction.OnSaveClick) }
                                .background(
                                    color = if (state.isSaveEnabled) {
                                        MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                                    } else {
                                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.08f)
                                    },
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(Padding.XS),
            verticalArrangement = Arrangement.spacedBy(Spacing.S),
        ) {
            CategoryTitleForm(
                categoryNameState = state.categoryName,
                onDone = { focusManager.clearFocus() }
            )
            CategoryTypeSelector(
                categoryType = state.categoryType,
                onTypeClicked = { onAction(CategoryEditorAction.OnTypeClicked(it)) }
            )
            if (state.categoryType == CategoryType.SUB) {
                ParentCategorySelector(
                    parentCategories = state.parentCategories,
                    selectedId = state.selectedParentCategory,
                    onParentCategoryClicked = { onAction(CategoryEditorAction.OnParentCategorySelected(it)) },
                )
            }
            IconSelectorSection(
                selectedIconName = state.selectedIconName,
                onIconSelected = { onAction(CategoryEditorAction.OnIconSelected(it)) },
            )
        }
    }
}