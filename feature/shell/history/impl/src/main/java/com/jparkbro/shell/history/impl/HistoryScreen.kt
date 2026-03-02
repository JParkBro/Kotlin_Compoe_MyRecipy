package com.jparkbro.shell.history.impl

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.theme.Border
import com.jparkbro.core.designsystem.theme.MyReceiptTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Spacing
import com.jparkbro.core.model.type.EditorType
import com.jparkbro.core.ui.component.MyReceiptConfirmDialog
import com.jparkbro.core.ui.util.ObserveAsEvents
import com.jparkbro.shell.history.impl.component.CategoryChipRow
import com.jparkbro.shell.history.impl.component.DateRangePicker
import com.jparkbro.shell.history.impl.component.DayHeader
import com.jparkbro.shell.history.impl.component.TransactionItem
import com.jparkbro.shell.history.impl.component.TransactionTypeFilter
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun HistoryRoot(
    bottomNavigation: @Composable () -> Unit,
    onNavigateToEditor: (EditorType, Long?) -> Unit,
    viewModel: HistoryViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    val deleteSuccessMessage = stringResource(R.string.delete_success)
    val deleteFailureMessage = stringResource(R.string.delete_failure)
    ObserveAsEvents(viewModel.event) { event ->
        when (event) {
            HistoryEvent.DeleteSuccess ->
                Toast.makeText(context, deleteSuccessMessage, Toast.LENGTH_SHORT).show()
            HistoryEvent.DeleteFailure ->
                Toast.makeText(context, deleteFailureMessage, Toast.LENGTH_SHORT).show()
        }
    }

    HistoryScreen(
        state = state,
        bottomNavigation = bottomNavigation,
        onAction = { action ->
            viewModel.onAction(action)
            when (action) {
                is HistoryAction.OnTransactionEditClick ->
                    onNavigateToEditor(EditorType.UPDATE, action.id)
                else -> Unit
            }
        },
    )
}

@Composable
private fun HistoryScreen(
    state: HistoryState,
    bottomNavigation: @Composable () -> Unit,
    onAction: (HistoryAction) -> Unit,
) {
    if (state.deletingTransactionId != null) {
        MyReceiptConfirmDialog(
            title = stringResource(R.string.transaction_delete_title),
            message = stringResource(R.string.transaction_delete_message),
            confirmText = stringResource(R.string.delete),
            cancelText = stringResource(R.string.cancel),
            onConfirm = { onAction(HistoryAction.OnTransactionDeleteConfirm) },
            onCancel = { onAction(HistoryAction.OnTransactionDeleteDismiss) },
        )
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .statusBarsPadding()
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = Padding.XS, vertical = Padding.XXS),
                    verticalArrangement = Arrangement.spacedBy(Spacing.XXS),
                ) {
                    DateRangePicker(
                        startDate = state.startDate,
                        endDate = state.endDate,
                        onStartDateSelected = { onAction(HistoryAction.OnStartDateSelected(it)) },
                        onEndDateSelected = { onAction(HistoryAction.OnEndDateSelected(it)) },
                    )
                    TransactionTypeFilter(
                        selected = state.selectedTransactionType,
                        onSelected = { onAction(HistoryAction.OnTransactionTypeSelected(it)) },
                    )
                }
                val subCategories = state.categories
                    .find { it.parent.id == state.selectedMainCategoryId }
                    ?.children
                    ?: emptyList()
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(Spacing.XXS),
                ) {
                    CategoryChipRow(
                        title = stringResource(R.string.category_main),
                        allLabel = stringResource(R.string.all),
                        categories = state.categories.map { it.parent },
                        selectedId = state.selectedMainCategoryId,
                        onSelected = { onAction(HistoryAction.OnMainCategorySelected(it)) },
                    )
                    CategoryChipRow(
                        title = stringResource(R.string.category_sub),
                        allLabel = stringResource(R.string.all_sub_category),
                        categories = subCategories,
                        selectedId = state.selectedSubCategoryId,
                        onSelected = { onAction(HistoryAction.OnSubCategorySelected(it)) },
                    )
                }
                HorizontalDivider(
                    thickness = Border.XS,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                    modifier = Modifier.padding(vertical = Padding.XS),
                )
            }
        },
        bottomBar = bottomNavigation,
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            state.transactionGroups.forEach { group ->
                item(key = group.date) {
                    DayHeader(group = group)
                }
                items(
                    items = group.transactions,
                    key = { it.id },
                ) { transaction ->
                    val mainCategory = state.categories
                        .find { it.parent.id == transaction.mainCategoryId }?.parent
                    val subCategory = state.categories
                        .flatMap { it.children }
                        .find { it.id == transaction.subCategoryId }
                    TransactionItem(
                        transaction = transaction,
                        mainCategoryTitle = mainCategory?.title,
                        subCategoryTitle = subCategory?.title,
                        categoryIconName = subCategory?.iconName ?: mainCategory?.iconName,
                        onEditClick = { onAction(HistoryAction.OnTransactionEditClick(transaction.id)) },
                        onDeleteClick = { onAction(HistoryAction.OnTransactionDeleteClick(transaction.id)) },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HistoryScreenPreview() {
    MyReceiptTheme {
        HistoryScreen(
            state = HistoryState(),
            bottomNavigation = {},
            onAction = {},
        )
    }
}
