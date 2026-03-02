package com.jparkbro.myreceipt

import android.app.Application
import com.jparkbro.category.editor.impl.di.categoryEditorModule
import com.jparkbro.category.manage.impl.di.categoryManageModule
import com.jparkbro.core.data.di.dataModule
import com.jparkbro.core.database.di.databaseModule
import com.jparkbro.shell.editor.impl.di.editorModule
import com.jparkbro.shell.history.impl.di.historyModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyReceiptApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyReceiptApplication)

            modules(
                databaseModule,
                dataModule,
                editorModule,
                historyModule,
                categoryManageModule,
                categoryEditorModule
            )
        }
    }
}