package com.jparkbro.shell.history.impl.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jparkbro.core.designsystem.theme.MyReceiptTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.shell.history.impl.DayTransactionGroup
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.abs

private val dateFormatter = DateTimeFormatter.ofPattern("M월 d일 (E)", Locale.KOREAN)

@Composable
internal fun DayHeader(
    group: DayTransactionGroup,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Padding.XS, vertical = Padding.XXS),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = LocalDate.parse(group.date).format(dateFormatter),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
        val isPositive = group.totalAmount >= 0
        Text(
            text = "%s%,d원".format(if (isPositive) "+" else "-", abs(group.totalAmount)),
            style = MaterialTheme.typography.labelMedium,
            color = if (isPositive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DayHeaderPreview() {
    MyReceiptTheme {
        DayHeader(
            group = DayTransactionGroup(
                date = "2025-01-15",
                totalAmount = 38000,
                transactions = emptyList(),
            )
        )
    }
}
