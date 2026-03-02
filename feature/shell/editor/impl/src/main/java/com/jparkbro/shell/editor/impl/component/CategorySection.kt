package com.jparkbro.shell.editor.impl.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.icon.SettingsOutlineIcon
import com.jparkbro.core.designsystem.icon.toImageVector
import com.jparkbro.core.designsystem.theme.Border
import com.jparkbro.core.designsystem.theme.IconSize
import com.jparkbro.core.designsystem.theme.MyRecipyTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Shape
import com.jparkbro.core.designsystem.theme.Spacing
import com.jparkbro.core.model.ui.CategoryGroupUiModel
import com.jparkbro.core.model.ui.CategoryUiModel
import com.jparkbro.core.ui.component.EditorFormSection

@Composable
internal fun CategorySection(
    categories: List<CategoryGroupUiModel>,
    selectedMainCategoryId: Long?,
    selectedSubCategoryId: Long?,
    onMainCategorySelected: (Long) -> Unit,
    onSubCategorySelected: (Long) -> Unit,
    onManageClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val selectedMain = categories.find { it.parent.id == selectedMainCategoryId }
    val subCategories = selectedMain?.children ?: emptyList()

    EditorFormSection(
        modifier = modifier,
        title = stringResource(R.string.category),
        trailingAction = {
            Row(
                modifier = Modifier.clickable { onManageClick() },
                horizontalArrangement = Arrangement.spacedBy(Spacing.XXXS),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = SettingsOutlineIcon,
                    contentDescription = stringResource(R.string.settings_icon),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(IconSize.XS),
                )
                Text(
                    text = stringResource(R.string.category_manage),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        },
    ) {
        if (categories.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(Shape.S)
                    )
                    .border(
                        width = Border.XS,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(Shape.S)
                    )
                    .padding(Padding.S),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Spacing.XXXS),
            ) {
                Text(
                    text = stringResource(R.string.category_empty_title),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                )
                Text(
                    text = stringResource(R.string.category_section_empty_action),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable { onManageClick() },
                )
            }
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(Spacing.XS)
            ) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.S),
                    contentPadding = PaddingValues(horizontal = Padding.XXXS),
                ) {
                    items(categories, key = { it.parent.id }) { group ->
                        val icon = group.parent.iconName.toImageVector() ?: return@items
                        CategoryItem(
                            icon = icon,
                            title = group.parent.title,
                            isSelected = group.parent.id == selectedMainCategoryId,
                            onClick = { onMainCategorySelected(group.parent.id) },
                        )
                    }
                }
                if (selectedMainCategoryId != null && subCategories.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colorScheme.onPrimary,
                                shape = RoundedCornerShape(Shape.S)
                            )
                            .border(
                                width = Border.XS,
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(Shape.S)
                            )
                            .padding(Padding.S),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(Spacing.XXXS),
                    ) {
                        Text(
                            text = stringResource(R.string.sub_category_empty_title),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        )
                        Text(
                            text = stringResource(R.string.category_section_empty_action),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable { onManageClick() },
                        )
                    }
                } else if (subCategories.isNotEmpty()) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colorScheme.onPrimary,
                                shape = RoundedCornerShape(Shape.M)
                            )
                            .border(
                                width = Border.XS,
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(Shape.M)
                            )
                            .padding(Padding.XS),
                        horizontalArrangement = Arrangement.spacedBy(Spacing.XS),
                        verticalArrangement = Arrangement.spacedBy(Spacing.XS),
                    ) {
                        subCategories.forEach { sub ->
                            SubCategoryChip(
                                sub = sub,
                                isSelected = sub.id == selectedSubCategoryId,
                                onClick = { onSubCategorySelected(sub.id) },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SubCategoryChip(
    sub: CategoryUiModel,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val icon = sub.iconName.toImageVector()

    Row(
        modifier = Modifier
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape,
            )
            .border(
                width = Border.XS,
                color = if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f),
                shape = CircleShape,
            )
            .clip(shape = CircleShape)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = Padding.XXS),
        horizontalArrangement = Arrangement.spacedBy(Spacing.XXXS),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) MaterialTheme.colorScheme.onPrimary
                       else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                modifier = Modifier.size(IconSize.XS),
            )
        }
        Text(
            text = sub.title,
            style = MaterialTheme.typography.labelMedium,
            color = if (isSelected) MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategorySectionPreview() {
    MyRecipyTheme {
        CategorySection(
            categories = emptyList(),
            selectedMainCategoryId = null,
            selectedSubCategoryId = null,
            onMainCategorySelected = {},
            onSubCategorySelected = {},
            onManageClick = {},
        )
    }
}
