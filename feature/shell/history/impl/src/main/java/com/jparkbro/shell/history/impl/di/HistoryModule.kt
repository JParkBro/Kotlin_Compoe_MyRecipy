package com.jparkbro.shell.history.impl.di

import com.jparkbro.shell.history.impl.HistoryViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val historyModule = module {
    viewModelOf(::HistoryViewModel)
}
