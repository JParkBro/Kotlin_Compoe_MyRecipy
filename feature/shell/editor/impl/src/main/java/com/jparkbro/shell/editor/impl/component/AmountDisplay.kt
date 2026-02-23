package com.jparkbro.shell.editor.impl.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.insert
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.icon.WonSymbolUnicode
import com.jparkbro.core.designsystem.theme.MyRecipyTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Spacing

@Composable
internal fun AmountDisplay(
    modifier: Modifier = Modifier,
    amount: TextFieldState,
    onDone: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Spacing.XXS)
    ) {
        Text(
            text = stringResource(R.string.amount),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
        )
        AmountInputTextField(
            amount = amount,
            onDone = onDone
        )
    }
}

@Composable
private fun AmountInputTextField(
    modifier: Modifier = Modifier,
    amount: TextFieldState,
    onDone: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    val numberInputTransformation = remember {
        InputTransformation {
            if (!asCharSequence().all { it.isDigit() } || length > 12) {
                revertAllChanges()
            }
        }
    }

    val thousandsOutputTransformation = remember {
        OutputTransformation {
            val originalLength = length

            for (i in originalLength - 3 downTo 1 step 3) {
                insert(i, ",")
            }
        }
    }
    val isEmptied = amount.text.isEmpty()

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = WonSymbolUnicode,
            style = MaterialTheme.typography.headlineSmall,
            color = if (isEmptied) MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f) else MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .align(Alignment.CenterStart)
        )
        BasicTextField(
            state = amount,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .padding(horizontal = Padding.M)
                .align(Alignment.Center),
            inputTransformation = numberInputTransformation,
            textStyle = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onSurface
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            onKeyboardAction = { onDone() },
            lineLimits = TextFieldLineLimits.SingleLine,
            interactionSource = interactionSource,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            outputTransformation = thousandsOutputTransformation,
            decorator = { innerBox ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = Padding.S, vertical = Padding.XS),
                ) {
                    if (isEmptied) {
                        Text(
                            text = "0",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                        )
                    }
                    innerBox()
                }
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AmountDisplayPreview() {
    MyRecipyTheme {
        AmountDisplay(
            amount = TextFieldState(),
            onDone = {}
        )
    }
}