package com.jparkbro.shell.editor.impl.di

import com.jparkbro.shell.editor.impl.EditorViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val editorModule = module {
    viewModelOf(::EditorViewModel)
}