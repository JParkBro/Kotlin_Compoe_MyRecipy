package com.jparkbro.shell.editor.impl.di

import com.jparkbro.core.model.type.EditorType
import com.jparkbro.shell.editor.impl.EditorViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val editorModule = module {
    viewModel { params ->
        EditorViewModel(
            editorType = params.get<EditorType>(),
            transactionId = params.getOrNull<Long>(),
            categoryRepository = get(),
            transactionRepository = get(),
        )
    }
}
