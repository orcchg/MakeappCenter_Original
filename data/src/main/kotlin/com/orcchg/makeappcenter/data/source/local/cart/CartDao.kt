package com.orcchg.makeappcenter.data.source.local.cart

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.orcchg.makeappcenter.domain.model.Product
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface CartDao {

    // TODO: 'products' database is not for Cart, its for products

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProductToCart(product: Product)

    @Query("SELECT COUNT(*) FROM products")
    fun cartSize(): Flowable<Int>

    @Query("DELETE FROM products")
    fun clearCart()

    @Query("SELECT * FROM products")
    fun productsInCart(): Maybe<List<Product>>
}
