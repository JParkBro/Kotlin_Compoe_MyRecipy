package com.jparkbro.core.ui.component

import androidx.annotation.StringRes
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jparkbro.core.designsystem.R
import com.jparkbro.core.designsystem.component.MyRecipyTopAppBar
import com.jparkbro.core.designsystem.icon.ArrowBackIcon

@Composable
fun BackStackTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int? = null,
    actions: @Composable (() -> Unit)? = null,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    onNavigationClick: () -> Unit = {},
) {
    MyRecipyTopAppBar(
        modifier = modifier,
        titleRes = titleRes,
        navigationIcon = ArrowBackIcon,
        navigationIconContentDescription = stringResource(R.string.arrow_back_icon),
        actions = actions,
        colors = colors,
        onNavigationClick = onNavigationClick,
    )
}