package com.jparkbro.category.manage.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jparkbro.core.ui.util.ObserveAsEvents
import com.jparkbro.category.manage.impl.component.CategoryEmptyView
import com.jparkbro.category.manage.impl.component.CategoryItemGroup
import com.jparkbro.category.manage.impl.component.HierarchyInfo
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.icon.AddIcon
import com.jparkbro.core.designsystem.theme.Border
import com.jparkbro.core.designsystem.theme.MyReceiptTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Spacing
import com.jparkbro.core.model.type.EditorType
import com.jparkbro.core.ui.component.BackStackTopAppBar
import com.jparkbro.core.ui.component.MyReceiptConfirmDialog
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun CategoryManageRoot(
    onNavigateBack: () -> Unit,
    onNavigateToCategoryEditor: (EditorType, Long?) -> Unit,
    viewModel: CategoryManageViewModel = koinViewModel()
) {

    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    val deleteSuccessMessage = stringResource(R.string.delete_success)
    val deleteFailureMessage = stringResource(R.string.delete_failure)
    ObserveAsEvents(viewModel.event) { event ->
        when (event) {
            CategoryManageEvent.DeleteSuccess ->
                Toast.makeText(context, deleteSuccessMessage, Toast.LENGTH_SHORT).show()
            CategoryManageEvent.DeleteFailure ->
                Toast.makeText(context, deleteFailureMessage, Toast.LENGTH_SHORT).show()
        }
    }

    CategoryManageScreen(
        state = state,
        onAction = { action ->
            viewModel.onAction(action)
            when (action) {
                CategoryManageAction.OnBackClick -> onNavigateBack()
                is CategoryManageAction.OnAddClick -> onNavigateToCategoryEditor(action.type, action.id)
                is CategoryManageAction.OnUpdateClick -> onNavigateToCategoryEditor(action.type, action.id)
                else -> Unit
            }
        }
    )
}

@Composable
private fun CategoryManageScreen(
    state: CategoryManageState,
    onAction: (CategoryManageAction) -> Unit
) {
    if (state.deletingCategoryId != null) {
        MyReceiptConfirmDialog(
            title = stringResource(R.string.category_delete_title),
            message = stringResource(R.string.category_delete_message),
            confirmText = stringResource(R.string.delete),
            cancelText = stringResource(R.string.cancel),
            onConfirm = { onAction(CategoryManageAction.OnDeleteConfirm) },
            onCancel = { onAction(CategoryManageAction.OnDeleteDismiss) },
        )
    }

    Scaffold(
        topBar = {
            Column {
                BackStackTopAppBar(
                    modifier = Modifier,
                    titleRes = R.string.category,
                    actions = {
                        Icon(
                            imageVector = AddIcon,
                            contentDescription = stringResource(R.string.add_icon),
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier
                                .padding(end = Padding.XXS)
                                .background(MaterialTheme.colorScheme.primary, CircleShape)
                                .clip(CircleShape)
                                .clickable {
                                    onAction(CategoryManageAction.OnAddClick(EditorType.INSERT, null))
                                }
                                .padding(Padding.XXXS)
                        )
                    },
                    onNavigationClick = { onAction(CategoryManageAction.OnBackClick) },
                )
                HorizontalDivider(
                    thickness = Border.XS,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(Padding.XS),
            verticalArrangement = Arrangement.spacedBy(Spacing.XS),
        ) {
            if (state.categories.isEmpty()) {
                CategoryEmptyView()
            } else {
                HierarchyInfo()
                state.categories.forEach { group ->
                    CategoryItemGroup(
                        category = group.parent,
                        subCategories = group.children,
                        onEdit = { id -> onAction(CategoryManageAction.OnUpdateClick(EditorType.UPDATE, id)) },
                        onDelete = { id -> onAction(CategoryManageAction.OnDeleteClick(id)) },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryManageScreenPreview() {
    MyReceiptTheme {
        CategoryManageScreen(
            state = CategoryManageState(),
            onAction = {}
        )
    }
}