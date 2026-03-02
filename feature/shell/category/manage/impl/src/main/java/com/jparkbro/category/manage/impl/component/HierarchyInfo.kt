package com.jparkbro.category.manage.impl.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.icon.AlertIcon
import com.jparkbro.core.designsystem.theme.Border
import com.jparkbro.core.designsystem.theme.MyReceiptTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Spacing

@Composable
internal fun HierarchyInfo(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                shape = RoundedCornerShape(Spacing.XXS)
            )
            .border(
                width = Border.XS,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(Spacing.XXS)
            )
            .padding(horizontal = Padding.XS, vertical = Padding.XXS),
        horizontalArrangement = Arrangement.spacedBy(Spacing.XXS),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = AlertIcon,
            contentDescription = stringResource(R.string.alert_icon),
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(R.string.hierarchy_info),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HierarchyInfoPreview() {
    MyReceiptTheme() {
        HierarchyInfo()
    }
}