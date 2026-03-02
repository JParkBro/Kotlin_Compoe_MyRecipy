package com.jparkbro.category.editor.impl.component

import com.jparkbro.core.ui.component.EditorFormSection
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.icon.KeyboardArrowDownIcon
import com.jparkbro.core.designsystem.icon.KeyboardArrowUpIcon
import com.jparkbro.core.designsystem.theme.Border
import com.jparkbro.core.designsystem.theme.IconSize
import com.jparkbro.core.designsystem.theme.MyRecipyTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Shape
import com.jparkbro.core.model.ui.CategoryUiModel

@Composable
internal fun ParentCategorySelector(
    parentCategories: List<CategoryUiModel>,
    selectedId: Long?,
    onParentCategoryClicked: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val selectedCategory = parentCategories.find { it.id == selectedId }

    EditorFormSection(
        title = stringResource(R.string.parent_category),
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(Shape.S))
                .clickable { expanded = true }
                .border(
                    width = Border.XS,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(Shape.S)
                )
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(Shape.S)
                )
                .padding(Padding.XS),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = selectedCategory?.title ?: stringResource(R.string.parent_category_hint),
                style = MaterialTheme.typography.bodyLarge,
                color = if (selectedCategory != null) {
                    MaterialTheme.colorScheme.onSurface
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                },
            )
            Icon(
                imageVector = if (expanded) KeyboardArrowUpIcon else KeyboardArrowDownIcon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(IconSize.S),
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Padding.XS)
                .border(
                    width = Border.XS,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(Shape.M)
                )
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(Shape.M)
                ),
            containerColor = Color.Transparent,
            tonalElevation = 0.dp,
            shadowElevation = 0.dp,
        ) {
            parentCategories.forEachIndexed { index, category ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = category.title,
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (category.id == selectedId) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurface
                            },
                        )
                    },
                    onClick = {
                        onParentCategoryClicked(category.id)
                        expanded = false
                    },
                )
                if (index < parentCategories.lastIndex) {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
                        thickness = Border.XS,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ParentCategorySelectorPreview() {
    MyRecipyTheme {
        ParentCategorySelector(
            parentCategories = listOf(
                CategoryUiModel(id = 1L, parentId = null, title = "고정지출", iconName = ""),
                CategoryUiModel(id = 2L, parentId = null, title = "식비", iconName = ""),
                CategoryUiModel(id = 3L, parentId = null, title = "교통", iconName = ""),
            ),
            selectedId = 1L,
            onParentCategoryClicked = {},
        )
    }
}
