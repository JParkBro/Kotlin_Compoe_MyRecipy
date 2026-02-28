package com.jparkbro.shell.editor.api

import androidx.navigation3.runtime.NavKey
import com.jparkbro.core.model.type.EditorType
import com.jparkbro.core.navigation.Navigator
import kotlinx.serialization.Serializable

@Serializable
data class EditorNavKey(
    val type: EditorType,
    val id: Long?
) : NavKey

fun Navigator.navigateToEditor(
    type: EditorType,
    id: Long? = null
) {
    navigate(EditorNavKey(type, id))
}