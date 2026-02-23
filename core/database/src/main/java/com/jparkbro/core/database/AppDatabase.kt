package com.jparkbro.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jparkbro.core.database.converter.Converters
import com.jparkbro.core.database.dao.CategoryDao
import com.jparkbro.core.database.dao.PaymentMethodDao
import com.jparkbro.core.database.dao.TransactionDao
import com.jparkbro.core.database.entity.CategoryEntity
import com.jparkbro.core.database.entity.PaymentMethodEntity
import com.jparkbro.core.database.entity.TransactionEntity

@Database(
    entities = [
        TransactionEntity::class,
        CategoryEntity::class,
        PaymentMethodEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
    abstract fun paymentMethodDao(): PaymentMethodDao
}
