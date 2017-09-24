package com.orcchg.makeappcenter.data.source.local.cart

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.orcchg.makeappcenter.domain.model.Product
import com.orcchg.makeappcenter.domain.util.Converters

@Database(entities = arrayOf(Product::class), version = 1)
@TypeConverters(value = Converters::class)
abstract class CartDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao
}
