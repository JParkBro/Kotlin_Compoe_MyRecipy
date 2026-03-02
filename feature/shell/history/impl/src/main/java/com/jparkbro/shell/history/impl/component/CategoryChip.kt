package com.jparkbro.shell.history.impl.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.theme.MyRecipyTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Shape
import com.jparkbro.core.designsystem.theme.Spacing
import com.jparkbro.core.model.ui.CategoryUiModel

@Composable
internal fun CategoryChipRow(
    title: String,
    allLabel: String,
    categories: List<CategoryUiModel>,
    selectedId: Long?,
    onSelected: (Long?) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = Padding.XS, end = Spacing.XXS),
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(Spacing.XXS),
            contentPadding = PaddingValues(end = Padding.XS),
        ) {
            item {
                CategoryChip(
                    label = allLabel,
                    selected = selectedId == null,
                    onClick = { onSelected(null) },
                )
            }
            items(categories) { category ->
                CategoryChip(
                    label = category.title,
                    selected = selectedId == category.id,
                    onClick = { onSelected(category.id) },
                )
            }
        }
    }
}

@Composable
private fun CategoryChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
    }
    val textColor = if (selected) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.primary
    }
    Text(
        text = label,
        style = MaterialTheme.typography.labelMedium,
        color = textColor,
        modifier = modifier
            .clip(RoundedCornerShape(Shape.M))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = Padding.XS, vertical = Padding.XXS),
    )
}

@Preview(showBackground = true)
@Composable
private fun CategoryChipRowPreview() {
    MyRecipyTheme {
        CategoryChipRow(
            title = "대분류",
            allLabel = stringResource(R.string.all),
            categories = listOf(
                CategoryUiModel(id = 1L, parentId = null, title = "식비", iconName = "ic_fork_spoon"),
                CategoryUiModel(id = 2L, parentId = null, title = "교통", iconName = "ic_directions_subway"),
            ),
            selectedId = null,
            onSelected = {},
        )
    }
}
