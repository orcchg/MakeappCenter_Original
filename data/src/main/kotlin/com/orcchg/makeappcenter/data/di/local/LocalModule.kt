package com.orcchg.makeappcenter.data.di.local

import android.arch.persistence.room.Room
import android.content.Context
import com.orcchg.makeappcenter.data.source.local.product.ProductDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule(private val context: Context) {

    /* Product */
    // --------------------------------------------------------------------------------------------
    @Provides @Singleton
    fun provideProductDatabase(): ProductDatabase {
        return Room.databaseBuilder(context, ProductDatabase::class.java, "products.db").build()
    }

    @Provides @Singleton
    fun provideProductDao(db: ProductDatabase) = db.productDao()
}