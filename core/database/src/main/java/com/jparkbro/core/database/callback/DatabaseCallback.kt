package com.jparkbro.core.database.callback

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class DatabaseCallback : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        db.execSQL(
            "INSERT INTO categories (parentId, title, iconName) VALUES (NULL, '고정지출', 'ic_receipt_long_fill')"
        )
        db.execSQL(
            "INSERT INTO categories (parentId, title, iconName) VALUES (last_insert_rowid(), '월세', 'ic_home')"
        )
    }
}
