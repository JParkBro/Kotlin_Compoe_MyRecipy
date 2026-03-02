package com.jparkbro.shell.editor.impl.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.theme.Border
import com.jparkbro.core.designsystem.theme.MyRecipyTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Shape
import com.jparkbro.core.ui.component.EditorFormSection

@Composable
internal fun MemoSection(
    modifier: Modifier = Modifier,
    memoState: TextFieldState,
) {
    val isEmpty = memoState.text.isEmpty()

    EditorFormSection(
        title = stringResource(R.string.memo),
        modifier = modifier,
    ) {
        BasicTextField(
            state = memoState,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 96.dp)
                .border(
                    width = Border.XS,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(Shape.S)
                )
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(Shape.S)
                ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurface
            ),
            lineLimits = TextFieldLineLimits.MultiLine(minHeightInLines = 4, maxHeightInLines = 4),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            decorator = { innerBox ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = Padding.S, vertical = Padding.XS),
                ) {
                    if (isEmpty) {
                        Text(
                            text = stringResource(R.string.memo_hint),
                            style = MaterialTheme.typography.bodyLarge,
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
private fun MemoSectionPreview() {
    MyRecipyTheme {
        MemoSection(memoState = TextFieldState())
    }
}
