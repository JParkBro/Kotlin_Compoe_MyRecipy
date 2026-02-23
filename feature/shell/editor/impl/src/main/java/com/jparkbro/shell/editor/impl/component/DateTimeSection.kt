package com.jparkbro.shell.editor.impl.component

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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
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
import com.jparkbro.core.designsystem.icon.TimerIcon
import com.jparkbro.core.designsystem.theme.Border
import com.jparkbro.core.designsystem.theme.IconSize
import com.jparkbro.core.designsystem.theme.MyRecipyTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Shape
import com.jparkbro.core.designsystem.theme.Spacing
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
internal fun DateTimeSection(
    modifier: Modifier = Modifier,
    date: LocalDate = LocalDate.now(),
    time: LocalTime = LocalTime.now(),
    onDateSelected: (LocalDate) -> Unit = {},
    onTimeSelected: (LocalTime) -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Spacing.XS)
    ) {
        DateDisplay(
            date = date,
            onDateSelected = onDateSelected,
            modifier = Modifier.weight(1f)
        )
        TimeDisplay(
            time = time,
            onTimeSelected = onTimeSelected,
            modifier = Modifier.weight(1f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateDisplay(
    modifier: Modifier = Modifier,
    date: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = date.atStartOfDay(ZoneId.of("UTC")).toInstant().toEpochMilli()
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Spacing.XXS)
    ) {
        Text(
            text = stringResource(R.string.date),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(Shape.M))
                .clickable { showDatePicker = true }
                .border(
                    width = Border.XS,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(Shape.M)
                )
                .background(MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(Shape.S))
                .padding(horizontal = Padding.XXS, vertical = Padding.XS),
            horizontalArrangement = Arrangement.spacedBy(Spacing.XXS),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = CalendarTodayIcon,
                contentDescription = stringResource(R.string.calendar_icon),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(IconSize.XS)
            )
            Text(
                text = date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val selectedDate = Instant.ofEpochMilli(millis)
                                .atZone(ZoneId.of("UTC"))
                                .toLocalDate()
                            onDateSelected(selectedDate)
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
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimeDisplay(
    modifier: Modifier = Modifier,
    time: LocalTime,
    onTimeSelected: (LocalTime) -> Unit,
) {
    var showTimePicker by remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState(
        initialHour = time.hour,
        initialMinute = time.minute,
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Spacing.XXS)
    ) {
        Text(
            text = stringResource(R.string.time),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(Shape.M))
                .clickable { showTimePicker = true }
                .border(
                    width = Border.XS,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(Shape.M)
                )
                .background(MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(Shape.S))
                .padding(horizontal = Padding.XXS, vertical = Padding.XS),
            horizontalArrangement = Arrangement.spacedBy(Spacing.XXS),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = TimerIcon,
                contentDescription = stringResource(R.string.timer_icon),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(IconSize.XS)
            )
            Text(
                text = time.format(DateTimeFormatter.ofPattern("HH:mm")),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }

    if (showTimePicker) {
        AlertDialog(
            onDismissRequest = { showTimePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        onTimeSelected(LocalTime.of(timePickerState.hour, timePickerState.minute))
                        showTimePicker = false
                    }
                ) {
                    Text(stringResource(R.string.confirm))
                }
            },
            dismissButton = {
                TextButton(onClick = { showTimePicker = false }) {
                    Text(stringResource(R.string.cancel))
                }
            },
            text = {
                TimePicker(state = timePickerState)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DateTimeSectionPreview() {
    MyRecipyTheme {
        DateTimeSection()
    }
}
