package com.jparkbro.core.data.di

import com.jparkbro.core.data.repository.CategoryRepository
import com.jparkbro.core.data.repository.CategoryRepositoryImpl
import com.jparkbro.core.data.repository.TransactionRepository
import com.jparkbro.core.data.repository.TransactionRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::CategoryRepositoryImpl) { bind<CategoryRepository>() }
    singleOf(::TransactionRepositoryImpl) { bind<TransactionRepository>() }
}
