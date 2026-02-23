package com.jparkbro.category.editor.api

import androidx.navigation3.runtime.NavKey
import com.jparkbro.core.model.EditorType
import com.jparkbro.core.navigation.Navigator
import kotlinx.serialization.Serializable

@Serializable
data class CategoryEditorNavKey(val type: EditorType, val id: Long?) : NavKey

fun Navigator.navigateToCategoryEditor(type: EditorType, id: Long? = null) {
    navigate(CategoryEditorNavKey(type, id))
}