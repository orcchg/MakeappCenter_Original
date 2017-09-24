package com.orcchg.makeappcenter.data.di.local

import android.arch.persistence.room.Room
import android.content.Context
import com.orcchg.makeappcenter.data.source.local.cart.CartDatabase
import com.orcchg.makeappcenter.data.source.local.shopify.checkout.CheckoutDatabase
import com.orcchg.makeappcenter.data.source.local.shopify.product.ProductDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule(private val context: Context) {

    /* Checkout */
    // --------------------------------------------------------------------------------------------
    @Provides @Singleton
    fun provideCheckoutDatabase(): CheckoutDatabase {
        return Room.databaseBuilder(context, CheckoutDatabase::class.java, "checkouts.db").build()
    }

    @Provides @Singleton
    fun provideCheckoutDao(db: CheckoutDatabase) = db.checkoutDao()

    /* Product */
    // --------------------------------------------------------------------------------------------
    @Provides @Singleton
    fun provideProductDatabase(): ProductDatabase {
        return Room.databaseBuilder(context, ProductDatabase::class.java, "products.db").build()
    }

    @Provides @Singleton
    fun provideProductDao(db: ProductDatabase) = db.productDao()

    /* Products in cart */
    // ------------------------------------------
    @Provides @Singleton
    fun provideCartDatabase(): CartDatabase {
        return Room.databaseBuilder(context, CartDatabase::class.java, "cart.db").build()
    }

    @Provides @Singleton
    fun provideCartDao(db: CartDatabase) = db.cartDao()
}
