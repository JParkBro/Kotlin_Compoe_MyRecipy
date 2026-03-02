package com.jparkbro.shell.history.impl.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.icon.MoreVertIcon
import com.jparkbro.core.designsystem.icon.toImageVector
import com.jparkbro.core.designsystem.theme.Border
import com.jparkbro.core.designsystem.theme.IconSize
import com.jparkbro.core.designsystem.theme.MyReceiptTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Shape
import com.jparkbro.core.designsystem.theme.Spacing
import com.jparkbro.core.model.type.TransactionType
import com.jparkbro.core.model.ui.TransactionUiModel

@Composable
internal fun TransactionItem(
    transaction: TransactionUiModel,
    mainCategoryTitle: String?,
    subCategoryTitle: String?,
    categoryIconName: String?,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val isIncome = transaction.transactionType == TransactionType.INCOME.name
    val amountColor = if (isIncome) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
    val amountText = "%s%,d원".format(if (isIncome) "+" else "-", transaction.amount)
    val typeLabel = if (isIncome) stringResource(R.string.income) else stringResource(R.string.expense)
    val categoryLabel = when {
        mainCategoryTitle != null && subCategoryTitle != null -> "$mainCategoryTitle > $subCategoryTitle"
        mainCategoryTitle != null -> mainCategoryTitle
        else -> "미분류"
    }
    val cardShape = RoundedCornerShape(Shape.S)
    var menuExpanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Padding.XS, vertical = Spacing.XXXS)
            .clip(cardShape)
            .border(
                width = Border.XS,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                shape = cardShape,
            )
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(Padding.XS),
        verticalArrangement = Arrangement.spacedBy(Spacing.XXS),
    ) {
        // Row 1: 지출/수입 타입 + 카테고리 | 시간 + 더보기
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(Spacing.XXS),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(IconSize.M),
                ) {
                    Text(
                        text = typeLabel,
                        style = MaterialTheme.typography.labelSmall,
                        color = amountColor,
                        modifier = Modifier
                            .clip(RoundedCornerShape(Shape.XS))
                            .background(amountColor.copy(alpha = 0.1f))
                            .padding(horizontal = Padding.XXXS, vertical = 2.dp),
                    )
                }
                Text(
                    text = categoryLabel,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = transaction.time,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Box {
                    IconButton(
                        onClick = { menuExpanded = true },
                        modifier = Modifier.size(IconSize.S),
                    ) {
                        Icon(
                            imageVector = MoreVertIcon,
                            contentDescription = stringResource(R.string.more_vert_icon),
                            modifier = Modifier.size(IconSize.XS),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false },
                        modifier = Modifier
                            .border(
                                width = Border.XS,
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(Shape.M),
                            )
                            .background(
                                color = MaterialTheme.colorScheme.onPrimary,
                                shape = RoundedCornerShape(Shape.M),
                            ),
                        containerColor = Color.Transparent,
                        tonalElevation = 0.dp,
                        shadowElevation = 0.dp,
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = stringResource(R.string.editor_edit_title),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth(),
                                )
                            },
                            contentPadding = PaddingValues(horizontal = Padding.XS),
                            onClick = {
                                menuExpanded = false
                                onEditClick()
                            },
                        )
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
                            thickness = Border.XS,
                        )
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = stringResource(R.string.delete),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.error,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth(),
                                )
                            },
                            contentPadding = PaddingValues(horizontal = Padding.XS),
                            onClick = {
                                menuExpanded = false
                                onDeleteClick()
                            },
                        )
                    }
                }
            }
        }

        // Row 2: 아이콘 + 내역 | 금액
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(Spacing.XXS),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f),
            ) {
                categoryIconName?.toImageVector()?.let { icon ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(IconSize.M)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(IconSize.XS),
                        )
                    }
                }
                Text(
                    text = subCategoryTitle ?: mainCategoryTitle ?: "미분류",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
            Text(
                text = amountText,
                style = MaterialTheme.typography.labelLarge,
                color = amountColor,
            )
        }

        // Row 3: 메모 (아이콘 너비만큼 들여쓰기)
        transaction.memo?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = IconSize.M + Spacing.XXS),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TransactionItemPreview() {
    MyReceiptTheme {
        TransactionItem(
            transaction = TransactionUiModel(
                id = 1L,
                date = "2025-01-15",
                time = "12:30",
                transactionType = TransactionType.EXPENSE.name,
                amount = 12000,
                memo = "점심 식사",
                mainCategoryId = 1L,
                subCategoryId = null,
            ),
            mainCategoryTitle = "식비",
            subCategoryTitle = "외식",
            categoryIconName = "ic_fork_spoon",
            onEditClick = {},
            onDeleteClick = {},
        )
    }
}
