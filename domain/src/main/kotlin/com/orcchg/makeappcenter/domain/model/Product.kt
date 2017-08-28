package com.orcchg.makeappcenter.domain.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.shopify.buy3.Storefront

@Entity(tableName = "products")
data class Product(@PrimaryKey @ColumnInfo(name = "id") val id: String) {

    companion object {
        fun from(product: Storefront.Product): Product {
            return Product(product.id.toString())
        }
    }
}
