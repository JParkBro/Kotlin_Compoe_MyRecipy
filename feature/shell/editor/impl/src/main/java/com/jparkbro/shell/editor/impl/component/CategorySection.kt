package com.jparkbro.shell.editor.impl.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.icon.SettingsOutlineIcon
import com.jparkbro.core.designsystem.theme.IconSize
import com.jparkbro.core.designsystem.theme.MyRecipyTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Spacing

private data class CategoryItemData(
    @DrawableRes val iconRes: Int,
    val title: String,
)

private val sampleCategories = listOf(
    CategoryItemData(iconRes = R.drawable.ic_receipt_long_outline, title = "식비"),
    CategoryItemData(iconRes = R.drawable.ic_timer,                title = "교통"),
    CategoryItemData(iconRes = R.drawable.ic_calendar_today,       title = "여가"),
    CategoryItemData(iconRes = R.drawable.ic_analytics_outline,    title = "의료"),
    CategoryItemData(iconRes = R.drawable.ic_add_notes_outline,    title = "쇼핑"),
    CategoryItemData(iconRes = R.drawable.ic_receipt_long_outline, title = "식비"),
    CategoryItemData(iconRes = R.drawable.ic_timer,                title = "교통"),
    CategoryItemData(iconRes = R.drawable.ic_calendar_today,       title = "여가"),
    CategoryItemData(iconRes = R.drawable.ic_analytics_outline,    title = "의료"),
    CategoryItemData(iconRes = R.drawable.ic_add_notes_outline,    title = "쇼핑"),
)

@Composable
internal fun CategorySection(
    onManageClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Spacing.XS),
    ) {
        CategorySectionHeader(
            onManageClick = onManageClick,
        )
        CategorySectionContent()
    }
}

@Composable
private fun CategorySectionHeader(
    onManageClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.category),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Row(
            modifier = Modifier
                .clickable { onManageClick() },
            horizontalArrangement = Arrangement.spacedBy(Spacing.XXXS),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = SettingsOutlineIcon,
                contentDescription = stringResource(R.string.settings_icon),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(IconSize.XS)
            )
            Text(
                text = stringResource(R.string.category_manage),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun CategorySectionContent(
    modifier: Modifier = Modifier,
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Spacing.XS),
        contentPadding = PaddingValues(horizontal = Padding.XXXS),
    ) {
        itemsIndexed(sampleCategories) { index, item ->
            CategoryItem(
                iconRes = item.iconRes,
                title = item.title,
                isSelected = selectedIndex == index,
                onClick = { selectedIndex = index },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategorySectionPreview() {
    MyRecipyTheme {
        CategorySection(
            onManageClick = {},
        )
    }
}
