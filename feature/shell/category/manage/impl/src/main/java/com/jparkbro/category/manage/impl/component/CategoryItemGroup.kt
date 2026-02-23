package com.jparkbro.category.manage.impl.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jparkbro.core.designsystem.icon.toImageVector
import com.jparkbro.core.designsystem.theme.MyRecipyTheme
import com.jparkbro.core.designsystem.theme.Spacing
import com.jparkbro.core.model.CategoryUiModel

@Composable
internal fun CategoryItemGroup(
    category: CategoryUiModel,
    subCategories: List<CategoryUiModel>,
    onEdit: (Long) -> Unit,
    onDelete: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isExpanded by remember { mutableStateOf(false) }

    val categoryIcon = category.iconName.toImageVector() ?: return

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Spacing.XXXS),
    ) {
        CategoryItem(
            icon = categoryIcon,
            title = category.title,
            isMajor = true,
            isExpanded = isExpanded,
            onEdit = { onEdit(category.id) },
            onDelete = { onDelete(category.id) },
            onToggle = { isExpanded = !isExpanded },
        )
        AnimatedVisibility(visible = isExpanded) {
            Column(
                verticalArrangement = Arrangement.spacedBy(Spacing.XXXS),
            ) {
                subCategories.forEach { sub ->
                    val subIcon = sub.iconName.toImageVector() ?: return@forEach
                    CategoryItem(
                        icon = subIcon,
                        title = sub.title,
                        isMajor = false,
                        onEdit = { onEdit(sub.id) },
                        onDelete = { onDelete(sub.id) },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryItemGroupPreview() {
    MyRecipyTheme {
        CategoryItemGroup(
            category = CategoryUiModel(id = 1L, title = "식비", iconName = "ic_fork_spoon"),
            subCategories = listOf(
                CategoryUiModel(id = 2L, title = "식당", iconName = "ic_restaurant"),
                CategoryUiModel(id = 3L, title = "카페", iconName = "ic_local_cafe"),
                CategoryUiModel(id = 4L, title = "피자", iconName = "ic_local_pizza"),
            ),
            onEdit = {},
            onDelete = {},
        )
    }
}
