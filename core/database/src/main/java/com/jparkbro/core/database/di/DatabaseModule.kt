package com.jparkbro.core.database.di

import androidx.room.Room
import com.jparkbro.core.database.AppDatabase
import com.jparkbro.core.database.callback.DatabaseCallback
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
                androidContext(),
                AppDatabase::class.java,
                "myrecipy.db",
            ).fallbackToDestructiveMigration(true)
            .addCallback(DatabaseCallback())
            .build()
    }

    single { get<AppDatabase>().transactionDao() }
    single { get<AppDatabase>().categoryDao() }
}
