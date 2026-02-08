package com.jparkbro.shell.settings.api

import androidx.navigation3.runtime.NavKey
import com.jparkbro.core.navigation.Navigator
import kotlinx.serialization.Serializable

@Serializable
object SettingsNavKey : NavKey

fun Navigator.navigateToSettings() {
    navigate(SettingsNavKey)
}