package com.jparkbro.myreceipt.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation3.runtime.NavKey
import com.jparkbro.core.designsystem.R

@Composable
internal fun BottomNavigation(
    currentKey: NavKey,
    onNavigate: (NavKey) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        BOTTOM_NAV_ITEMS.forEach { (navKey, item) ->
            val isSelected = currentKey::class == navKey::class

            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(navKey) },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(if (isSelected) item.selectedIcon else item.unselectedIcon),
                        contentDescription = stringResource(R.string.nav_icon),
                        tint = if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                label = {
                    Text(
                        text = stringResource(item.iconTextId),
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )
        }
    }
}