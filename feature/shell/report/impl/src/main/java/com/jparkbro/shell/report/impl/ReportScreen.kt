package com.jparkbro.shell.report.impl

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun ReportRoot(
    bottomNavigation: @Composable () -> Unit
) {

    ReportScreen(
        bottomNavigation = bottomNavigation
    )
}

@Composable
private fun ReportScreen(
    bottomNavigation: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = bottomNavigation
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Report Screen",
            )
        }
    }
}