package com.orcchg.makeappcenter.data.source.local.shopify.checkout

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.orcchg.makeappcenter.domain.model.Checkout

@Database(entities = arrayOf(Checkout::class), version = 1)
abstract class CheckoutDatabase : RoomDatabase() {

    abstract fun checkoutDao(): CheckoutDao
}
