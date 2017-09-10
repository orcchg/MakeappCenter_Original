package com.orcchg.makeappcenter.data.source.local.product

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.orcchg.makeappcenter.domain.model.Product
import io.reactivex.Flowable

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProductToCart(product: Product)

    @Query("SELECT COUNT(*) FROM products")
    fun cartSize(): Flowable<Int>

    @Query("SELECT * FROM products")
    fun productsInCart(): Flowable<List<Product>>
}
