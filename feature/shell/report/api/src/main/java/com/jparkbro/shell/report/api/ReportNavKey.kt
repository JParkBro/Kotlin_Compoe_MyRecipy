package com.jparkbro.shell.report.api

import androidx.navigation3.runtime.NavKey
import com.jparkbro.core.navigation.Navigator
import kotlinx.serialization.Serializable

@Serializable
object ReportNavKey : NavKey

fun Navigator.navigateToReport() {
    navigate(ReportNavKey)
}