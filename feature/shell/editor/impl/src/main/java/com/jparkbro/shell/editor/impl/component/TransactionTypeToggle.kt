package com.jparkbro.shell.editor.impl.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.theme.Border
import com.jparkbro.core.designsystem.theme.MyRecipyTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Shape
import com.jparkbro.core.model.TransactionType

@Composable
internal fun TransactionTypeToggle(
    modifier: Modifier = Modifier,
    type: TransactionType = TransactionType.INCOME,
    onTypeClicked: (TransactionType) -> Unit = {},
) {
    val isIncome = type == TransactionType.INCOME

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(Shape.S)
            )
            .padding(Padding.XXXS)
    ) {
        ToggleItem(
            text = stringResource(R.string.income),
            selected = isIncome,
            onClick = { onTypeClicked(TransactionType.INCOME) },
            modifier = Modifier.weight(1f),
        )
        ToggleItem(
            text = stringResource(R.string.expense),
            selected = !isIncome,
            onClick = { onTypeClicked(TransactionType.EXPENSE) },
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun ToggleItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
        textAlign = TextAlign.Center,
        modifier = modifier
            .background(
                color = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = RoundedCornerShape(Shape.S)
            )
            .clip(shape = RoundedCornerShape(Shape.S))
            .clickable { onClick() }
            .padding(vertical = Padding.XXS)
    )
}

@Preview(showBackground = true)
@Composable
private fun TransactionTypeTogglePreview() {
    MyRecipyTheme {
        TransactionTypeToggle()
    }
}