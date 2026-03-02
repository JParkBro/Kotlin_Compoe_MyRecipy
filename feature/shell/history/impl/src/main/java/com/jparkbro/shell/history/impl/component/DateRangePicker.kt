package com.jparkbro.shell.history.impl.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.icon.CalendarTodayIcon
import com.jparkbro.core.designsystem.theme.Border
import com.jparkbro.core.designsystem.theme.IconSize
import com.jparkbro.core.designsystem.theme.MyRecipyTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Shape
import com.jparkbro.core.designsystem.theme.Spacing
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private fun LocalDate.toUtcMillis(): Long =
    atStartOfDay(ZoneId.of("UTC")).toInstant().toEpochMilli()

@Composable
internal fun DateRangePicker(
    startDate: LocalDate?,
    endDate: LocalDate?,
    onStartDateSelected: (LocalDate) -> Unit,
    onEndDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.XXS),
    ) {
        DateChip(
            date = startDate,
            placeholder = stringResource(R.string.start_date),
            maxDate = endDate,
            onDateSelected = onStartDateSelected,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = "~",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        DateChip(
            date = endDate,
            placeholder = stringResource(R.string.end_date),
            minDate = startDate,
            onDateSelected = onEndDateSelected,
            modifier = Modifier.weight(1f),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateChip(
    date: LocalDate?,
    placeholder: String,
    modifier: Modifier = Modifier,
    onDateSelected: (LocalDate) -> Unit,
    minDate: LocalDate? = null,
    maxDate: LocalDate? = null,
) {
    var showDatePicker by remember { mutableStateOf(false) }

    val selectableDates = remember(minDate, maxDate) {
        object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                val afterMin = minDate?.let { utcTimeMillis >= it.toUtcMillis() } ?: true
                val beforeMax = maxDate?.let { utcTimeMillis <= it.toUtcMillis() } ?: true
                return afterMin && beforeMax
            }
        }
    }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = (date ?: LocalDate.now()).toUtcMillis(),
        selectableDates = selectableDates,
    )

    val isSelected = date != null
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Shape.M))
            .clickable { showDatePicker = true }
            .border(
                width = Border.XS,
                color = MaterialTheme.colorScheme.primary.copy(alpha = if (isSelected) 0.4f else 0.2f),
                shape = RoundedCornerShape(Shape.M),
            )
            .background(MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(Shape.S))
            .padding(horizontal = Padding.XXXS, vertical = Padding.XS),
        horizontalArrangement = Arrangement.spacedBy(Spacing.XXS, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = CalendarTodayIcon,
            contentDescription = stringResource(R.string.calendar_today_icon),
            tint = MaterialTheme.colorScheme.primary.copy(alpha = if (isSelected) 1f else 0.4f),
            modifier = Modifier.size(IconSize.XS),
        )
        Text(
            text = date?.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")) ?: placeholder,
            style = MaterialTheme.typography.bodyLarge,
            color = if (isSelected) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
            },
        )
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val selected = Instant.ofEpochMilli(millis)
                                .atZone(ZoneId.of("UTC"))
                                .toLocalDate()
                            onDateSelected(selected)
                        }
                        showDatePicker = false
                    }
                ) {
                    Text(stringResource(R.string.confirm))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text(stringResource(R.string.cancel))
                }
            },
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DateRangePickerPreview() {
    MyRecipyTheme {
        DateRangePicker(
            startDate = null,
            endDate = null,
            onStartDateSelected = {},
            onEndDateSelected = {},
        )
    }
}
