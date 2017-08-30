package com.orcchg.makeappcenter.domain.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.shopify.buy3.Storefront

@Entity(tableName = "products")
data class ProductCollection(@PrimaryKey @ColumnInfo(name = "id") var id: String = "",
                             @ColumnInfo(name = "products") var products: List<Product>,
                             @ColumnInfo(name = "title") var title: String = "") {

    companion object {
        fun from(collection: Storefront.Collection): ProductCollection {
            val products = arrayListOf<Product>()
            collection.products.edges.forEach { products.add(Product.from(it.node)) }
            return ProductCollection(id = collection.id.toString(), products = products, title = collection.title)
        }
    }
}
