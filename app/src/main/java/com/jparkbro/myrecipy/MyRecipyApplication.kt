package com.jparkbro.myrecipy

import android.app.Application
import com.jparkbro.category.manage.impl.di.categoryManageModule
import com.jparkbro.core.database.di.databaseModule
import com.jparkbro.shell.editor.impl.di.editorModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyRecipyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyRecipyApplication)

            modules(
                databaseModule,
                editorModule,
                categoryManageModule
            )
        }
    }
}