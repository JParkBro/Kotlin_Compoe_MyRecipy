package com.jparkbro.category.manage.impl.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.icon.DeleteIcon
import com.jparkbro.core.designsystem.icon.EditIcon
import com.jparkbro.core.designsystem.icon.KeyboardArrowDownIcon
import com.jparkbro.core.designsystem.icon.KeyboardArrowUpIcon
import com.jparkbro.core.designsystem.icon.RestaurantIcon
import com.jparkbro.core.designsystem.theme.Border
import com.jparkbro.core.designsystem.theme.IconSize
import com.jparkbro.core.designsystem.theme.MyRecipyTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Shape
import com.jparkbro.core.designsystem.theme.Spacing

@Composable
internal fun CategoryItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    isMajor: Boolean,
    isExpanded: Boolean = false,
    isExpandable: Boolean = true,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onToggle: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(Shape.S)
            )
            .border(
                width = Border.XS,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(Shape.S)
            )
            .padding(Padding.XS),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Spacing.XXS),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isMajor) MaterialTheme.colorScheme.primary
                       else MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                modifier = Modifier
                    .background(
                        color = if (isMajor) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                else Color.Transparent,
                        shape = RoundedCornerShape(Shape.XS)
                    )
                    .padding(Padding.XXXS)
            )
            Text(
                text = title,
                style = if (isMajor) MaterialTheme.typography.titleSmall
                        else MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing.XXS),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = EditIcon,
                contentDescription = stringResource(R.string.edit_icon),
                tint = if (isMajor) MaterialTheme.colorScheme.primary
                       else MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                modifier = Modifier
                    .clickable { onEdit() }
            )
            Icon(
                imageVector = DeleteIcon,
                contentDescription = stringResource(R.string.delete_icon),
                tint = if (isMajor) MaterialTheme.colorScheme.error
                       else MaterialTheme.colorScheme.error.copy(alpha = 0.7f),
                modifier = Modifier
                    .clickable { onDelete() }
            )
            if (isMajor) {
                Icon(
                    imageVector = if (isExpanded) KeyboardArrowUpIcon else KeyboardArrowDownIcon,
                    contentDescription = if (isExpanded) stringResource(R.string.keyboard_arrow_up_icon)
                                         else stringResource(R.string.keyboard_arrow_down_icon),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                        alpha = if (isExpandable) 0.5f else 0.15f
                    ),
                    modifier = Modifier.clickable(enabled = isExpandable) { onToggle() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryItemPreview() {
    var isExpanded by remember { mutableStateOf(false) }
    MyRecipyTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.XXS)
        ) {
            CategoryItem(
                icon = RestaurantIcon,
                title = "식비",
                isMajor = true,
                isExpanded = isExpanded,
                onEdit = {},
                onDelete = {},
                onToggle = { isExpanded = !isExpanded },
            )
            CategoryItem(
                icon = RestaurantIcon,
                title = "식비",
                isMajor = false,
                isExpanded = isExpanded,
                onEdit = {},
                onDelete = {},
                onToggle = { isExpanded = !isExpanded },
            )
        }
    }
}