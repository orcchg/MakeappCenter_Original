package com.orcchg.makeappcenter.data.source.local.product

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.orcchg.makeappcenter.domain.model.Product

@Database(entities = arrayOf(Product::class), version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}
