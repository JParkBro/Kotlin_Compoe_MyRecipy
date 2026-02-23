package com.jparkbro.category.manage.impl.di

import com.jparkbro.category.manage.impl.CategoryManageViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val categoryManageModule = module {
    viewModelOf(::CategoryManageViewModel)
}