package com.jparkbro.shell.history.impl.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.component.SegmentedToggle
import com.jparkbro.core.designsystem.theme.MyRecipyTheme
import com.jparkbro.core.model.type.TransactionType

@Composable
internal fun TransactionTypeFilter(
    selected: TransactionType?,
    onSelected: (TransactionType?) -> Unit,
    modifier: Modifier = Modifier,
) {
    val items = listOf(
        stringResource(R.string.all),
        stringResource(R.string.income),
        stringResource(R.string.expense),
    )
    val selectedIndex = when (selected) {
        null -> 0
        TransactionType.INCOME -> 1
        TransactionType.EXPENSE -> 2
    }
    SegmentedToggle(
        items = items,
        selectedIndex = selectedIndex,
        onItemSelected = { index ->
            onSelected(when (index) {
                1 -> TransactionType.INCOME
                2 -> TransactionType.EXPENSE
                else -> null
            })
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun TransactionTypeFilterPreview() {
    MyRecipyTheme {
        TransactionTypeFilter(selected = null, onSelected = {})
    }
}
