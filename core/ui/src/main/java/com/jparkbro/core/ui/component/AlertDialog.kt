package com.jparkbro.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.jparkbro.core.designsystem.component.BaseDialog
import com.jparkbro.core.designsystem.theme.MyReceiptTheme
import com.jparkbro.core.designsystem.theme.Padding
import com.jparkbro.core.designsystem.theme.Shape
import com.jparkbro.core.designsystem.theme.Spacing

@Composable
fun MyReceiptAlertDialog(
    title: String,
    message: String,
    confirmText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    BaseDialog(onDismiss = onDismiss) {
        DialogBody(title = title, message = message)
        DialogConfirmButton(
            text = confirmText,
            onClick = onConfirm,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
internal fun DialogBody(
    title: String,
    message: String,
) {
    Column(verticalArrangement = Arrangement.spacedBy(Spacing.XXS)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
internal fun DialogConfirmButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onPrimary,
        textAlign = TextAlign.Center,
        modifier = modifier
            .clip(RoundedCornerShape(Shape.S))
            .background(MaterialTheme.colorScheme.primary)
            .clickable { onClick() }
            .padding(vertical = Padding.XXS),
    )
}

@Composable
internal fun DialogCancelButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = TextAlign.Center,
        modifier = modifier
            .clip(RoundedCornerShape(Shape.S))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable { onClick() }
            .padding(vertical = Padding.XXS),
    )
}

@Preview(showBackground = true)
@Composable
private fun AlertDialogPreview() {
    MyReceiptTheme {
        MyReceiptAlertDialog(
            title = "알림",
            message = "요청이 처리되었습니다.",
            confirmText = "확인",
            onConfirm = {},
            onDismiss = {},
        )
    }
}
