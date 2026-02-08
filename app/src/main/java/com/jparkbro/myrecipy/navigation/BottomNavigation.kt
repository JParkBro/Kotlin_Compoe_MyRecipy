package com.jparkbro.myrecipy.navigation

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

@Composable
internal fun BottomNavigation(
    currentKey: NavKey,
    onNavigate: (NavKey) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        TOP_LEVEL_NAV_ITEMS.forEach { (navKey, item) ->
            val isSelected = currentKey == navKey

            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(navKey) },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(if (isSelected) item.selectedIcon else item.unselectedIcon),
                        contentDescription = null,
                        tint = if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                label = {
                    Text(
                        text = stringResource(item.iconTextId),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            )
        }
    }
}