package com.jparkbro.category.editor.impl.component

import com.jparkbro.core.ui.component.EditorFormSection
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.icon.CategoryIcons
import com.jparkbro.core.designsystem.icon.toImageVector
import com.jparkbro.core.designsystem.theme.MyRecipyTheme
import com.jparkbro.core.designsystem.theme.Spacing
import com.jparkbro.core.ui.layout.rememberGridInfo

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun IconSelectorSection(
    selectedIconName: String?,
    onIconSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    EditorFormSection(
        title = stringResource(R.string.icon_select),
        trailingAction = {
            Text(
                text = if (isExpanded) stringResource(R.string.show_less) else stringResource(R.string.show_all),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { isExpanded = !isExpanded },
            )
        },
        modifier = modifier,
    ) {
        BoxWithConstraints {
            val gridInfo = rememberGridInfo(
                availableWidth = maxWidth,
                spacing = Spacing.XXS,
                defaultItemWidth = 80.dp,
                minColumns = 4,
                maxColumns = 8,
            )

            val remainder = CategoryIcons.size % gridInfo.columns

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(Spacing.XXS),
                verticalArrangement = Arrangement.spacedBy(Spacing.XXS),
                maxItemsInEachRow = gridInfo.columns,
                maxLines = if (isExpanded) Int.MAX_VALUE else 2,
            ) {
                CategoryIcons.forEach { iconName ->
                    val icon = iconName.toImageVector() ?: return@forEach
                    SelectableIconItem(
                        icon = icon,
                        isSelected = selectedIconName == iconName,
                        onClick = { onIconSelected(iconName) },
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                    )
                }
                if (remainder != 0) {
                    repeat(gridInfo.columns - remainder) {
                        Box(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IconSelectorSectionPreview() {
    MyRecipyTheme {
        IconSelectorSection(
            selectedIconName = "ic_fork_spoon",
            onIconSelected = {},
        )
    }
}
