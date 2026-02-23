package com.jparkbro.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["majorCategoryId"],
            onDelete = ForeignKey.SET_NULL,
        ),
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["middleCategoryId"],
            onDelete = ForeignKey.SET_NULL,
        ),
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["minorCategoryId"],
            onDelete = ForeignKey.SET_NULL,
        ),
        ForeignKey(
            entity = PaymentMethodEntity::class,
            parentColumns = ["id"],
            childColumns = ["paymentMethodId"],
            onDelete = ForeignKey.SET_NULL,
        ),
    ],
    indices = [
        Index("majorCategoryId"),
        Index("middleCategoryId"),
        Index("minorCategoryId"),
        Index("paymentMethodId"),
    ],
)
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: String,
    val time: String,
    val transactionType: String,
    val amount: Long,
    val memo: String,
    val majorCategoryId: Long?,
    val middleCategoryId: Long?,
    val minorCategoryId: Long?,
    val paymentMethodId: Long?,
)
