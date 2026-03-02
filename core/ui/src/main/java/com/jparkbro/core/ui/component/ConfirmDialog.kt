package com.jparkbro.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jparkbro.core.designsystem.component.BaseDialog
import com.jparkbro.core.designsystem.theme.MyReceiptTheme
import com.jparkbro.core.designsystem.theme.Spacing

@Composable
fun MyReceiptConfirmDialog(
    title: String,
    message: String,
    confirmText: String,
    cancelText: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    onDismiss: () -> Unit = onCancel,
) {
    BaseDialog(onDismiss = onDismiss) {
        DialogBody(title = title, message = message)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.XXS),
        ) {
            DialogCancelButton(
                text = cancelText,
                onClick = onCancel,
                modifier = Modifier.weight(1f),
            )
            DialogConfirmButton(
                text = confirmText,
                onClick = onConfirm,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ConfirmDialogPreview() {
    MyReceiptTheme {
        MyReceiptConfirmDialog(
            title = "카테고리 삭제",
            message = "해당 카테고리를 삭제하시겠습니까?",
            confirmText = "삭제",
            cancelText = "취소",
            onConfirm = {},
            onCancel = {},
        )
    }
}
