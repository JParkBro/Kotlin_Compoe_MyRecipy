package com.jparkbro.shell.editor.api

import androidx.navigation3.runtime.NavKey
import com.jparkbro.core.navigation.Navigator
import kotlinx.serialization.Serializable

@Serializable
object EditorNavKey : NavKey

fun Navigator.navigateToEditor() {
    navigate(EditorNavKey)
}