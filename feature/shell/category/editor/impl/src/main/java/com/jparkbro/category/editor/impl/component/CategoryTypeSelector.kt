package com.jparkbro.category.editor.impl.component

import com.jparkbro.core.ui.component.EditorFormSection
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.component.SegmentedToggle
import com.jparkbro.core.designsystem.theme.MyReceiptTheme
import com.jparkbro.core.model.type.CategoryType

@Composable
internal fun CategoryTypeSelector(
    categoryType: CategoryType,
    onTypeClicked: (CategoryType) -> Unit,
    modifier: Modifier = Modifier,
) {
    EditorFormSection(
        title = stringResource(R.string.category_type),
        modifier = Modifier,
    ) {
        SegmentedToggle(
            items = listOf(stringResource(R.string.category_main), stringResource(R.string.category_sub)),
            selectedIndex = if (categoryType == CategoryType.MAIN) 0 else 1,
            onItemSelected = { index ->
                onTypeClicked(if (index == 0) CategoryType.MAIN else CategoryType.SUB)
            },
            modifier = modifier,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryTypeSelectorPreview() {
    MyReceiptTheme {
        CategoryTypeSelector(
            categoryType = CategoryType.MAIN,
            onTypeClicked = {},
        )
    }
}
