package com.jparkbro.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jparkbro.core.database.entity.PaymentMethodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentMethodDao {

    @Insert
    suspend fun insert(paymentMethod: PaymentMethodEntity)

    @Update
    suspend fun update(paymentMethod: PaymentMethodEntity)

    @Delete
    suspend fun delete(paymentMethod: PaymentMethodEntity)

    @Query("SELECT * FROM payment_methods")
    fun getAll(): Flow<List<PaymentMethodEntity>>
}
