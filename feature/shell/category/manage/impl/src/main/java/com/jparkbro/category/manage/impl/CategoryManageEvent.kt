package com.jparkbro.category.manage.impl

sealed interface CategoryManageEvent {
    data object DeleteSuccess : CategoryManageEvent
    data object DeleteFailure : CategoryManageEvent
}
