package com.jparkbro.category.editor.impl.di

import com.jparkbro.category.editor.impl.CategoryEditorViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val categoryEditorModule = module {
    viewModelOf(::CategoryEditorViewModel)
}