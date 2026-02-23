package com.jparkbro.category.manage.api

import androidx.navigation3.runtime.NavKey
import com.jparkbro.core.navigation.Navigator
import kotlinx.serialization.Serializable

@Serializable
object CategoryManageNavKey : NavKey

fun Navigator.navigateToCategoryManage() {
    navigate(CategoryManageNavKey)
}