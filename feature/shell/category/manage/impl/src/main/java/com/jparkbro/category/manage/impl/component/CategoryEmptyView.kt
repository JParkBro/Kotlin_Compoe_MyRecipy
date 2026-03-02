package com.jparkbro.category.manage.impl.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.icon.AddNotesOutlineIcon
import com.jparkbro.core.designsystem.theme.IconSize
import com.jparkbro.core.designsystem.theme.MyReceiptTheme
import com.jparkbro.core.designsystem.theme.Spacing

@Composable
internal fun CategoryEmptyView(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = AddNotesOutlineIcon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
            modifier = Modifier.size(IconSize.XXL),
        )
        Spacer(modifier = Modifier.height(Spacing.S))
        Text(
            text = stringResource(R.string.category_empty_title),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            textAlign = TextAlign.Center,
        )
        Text(
            text = stringResource(R.string.category_empty_description),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryEmptyViewPreview() {
    MyReceiptTheme {
        CategoryEmptyView()
    }
}
