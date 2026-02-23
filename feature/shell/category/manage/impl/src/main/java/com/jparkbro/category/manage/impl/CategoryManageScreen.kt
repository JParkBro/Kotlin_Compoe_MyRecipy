package com.jparkbro.category.manage.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jparkbro.category.manage.impl.component.CategoryItemGroup
import com.jparkbro.category.manage.impl.component.HierarchyInfo
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.icon.AddIcon
import com.jparkbro.core.designsystem.theme.Border
import com.jparkbro.core.designsystem.theme.MyRecipyTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Spacing
import com.jparkbro.core.model.CategoryUiModel
import com.jparkbro.core.model.EditorType
import com.jparkbro.core.ui.component.BackStackTopAppBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun CategoryManageRoot(
    onNavigateBack: () -> Unit,
    onNavigateToCategoryEditor: (EditorType, Long?) -> Unit,
    viewModel: CategoryManageViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    CategoryManageScreen(
        state = state,
        onAction = { action ->
            when (action) {
                CategoryManageAction.OnBackClick -> onNavigateBack()
                is CategoryManageAction.OnAddClick -> onNavigateToCategoryEditor(action.type, action.id)
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
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            contentPadding = PaddingValues(Padding.XS),
            verticalArrangement = Arrangement.spacedBy(Spacing.XS)
        ) {
            stickyHeader {
                HierarchyInfo(
                    modifier = Modifier
                )
            }
            items(10) {
                CategoryItemGroup(
                    category = CategoryUiModel(
                        id = 1,
                        title = "식비",
                        iconName = "ic_alert"
                    ),
                    subCategories = emptyList(),
                    onEdit = {},
                    onDelete = {},
                    modifier = Modifier
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryManageScreenPreview() {
    MyRecipyTheme {
        CategoryManageScreen(
            state = CategoryManageState(),
            onAction = {}
        )
    }
}