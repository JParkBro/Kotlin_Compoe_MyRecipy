package com.jparkbro.category.editor.impl.component

import com.jparkbro.core.ui.component.EditorFormSection
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.theme.Border
import com.jparkbro.core.designsystem.theme.MyRecipyTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Shape

@Composable
internal fun CategoryTitleForm(
    modifier: Modifier = Modifier,
    categoryNameState: TextFieldState,
    onDone: () -> Unit
) {

    val isEmptied = categoryNameState.text.isEmpty()

    EditorFormSection(
        title = stringResource(R.string.category_name),
        modifier = Modifier,
    ) {
        BasicTextField(
            state = categoryNameState,
            modifier = modifier
                .fillMaxWidth()
                .border(
                    width = Border.XS,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(Shape.S)
                )
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(Shape.S)
                ),
            inputTransformation = InputTransformation.maxLength(20),
            textStyle = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onSurface
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            onKeyboardAction = { onDone() },
            lineLimits = TextFieldLineLimits.SingleLine,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            decorator = { innerBox ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = Padding.S, vertical = Padding.XS),
                ) {
                    if (isEmptied) {
                        Text(
                            text = stringResource(R.string.category_name_hint),
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
private fun CategoryTitleFormPreview() {
    MyRecipyTheme {
        CategoryTitleForm(
            categoryNameState = TextFieldState(),
            onDone = {}
        )
    }
}