package com.jparkbro.category.editor.impl.di

import com.jparkbro.category.editor.impl.CategoryEditorViewModel
import com.jparkbro.core.model.type.EditorType
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val categoryEditorModule = module {
    viewModel { params ->
        CategoryEditorViewModel(
            editorType = params.get<EditorType>(),
            categoryId = params.getOrNull<Long>(),
            categoryRepository = get(),
        )
    }
}
