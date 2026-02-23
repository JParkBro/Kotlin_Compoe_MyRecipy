package com.jparkbro.category.manage.impl

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoryManageViewModel(

) : ViewModel() {

    private val _state = MutableStateFlow(CategoryManageState())
    val state = _state.asStateFlow()

    init {

    }

    fun onAction(action: CategoryManageAction) {
        when (action) {

            else -> Unit
        }
    }
}