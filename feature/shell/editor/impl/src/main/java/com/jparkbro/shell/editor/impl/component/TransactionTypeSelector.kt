package com.jparkbro.shell.editor.impl.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.component.SegmentedToggle
import com.jparkbro.core.designsystem.theme.MyReceiptTheme
import com.jparkbro.core.model.type.TransactionType

@Composable
internal fun TransactionTypeSelector(
    modifier: Modifier = Modifier,
    type: TransactionType = TransactionType.INCOME,
    onTypeClicked: (TransactionType) -> Unit = {},
) {
    SegmentedToggle(
        items = listOf(stringResource(R.string.income), stringResource(R.string.expense)),
        selectedIndex = if (type == TransactionType.INCOME) 0 else 1,
        onItemSelected = { index ->
            onTypeClicked(if (index == 0) TransactionType.INCOME else TransactionType.EXPENSE)
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun TransactionTypeSelectorPreview() {
    MyReceiptTheme {
        TransactionTypeSelector()
    }
}
